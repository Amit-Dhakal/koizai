package com.koizai.commonservice.currency.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.koizai.commonservice.common.*;
import com.koizai.commonservice.currency.model.CurrencyEnum;
import com.koizai.commonservice.currency.model.CurrencyExchange;
import com.koizai.commonservice.currency.model.CurrencyUpdate;
import com.koizai.commonservice.currency.model.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class CurrencyService implements Constants {
    private static String JSON_FILENAME = "currency-exchanges";
    private final OpenRateService openRateService;
    private final ObjectMapper objectMapper;

    private List<CurrencyExchange> currencyExchanges = new ArrayList<>();
    private Map<String, CurrencyExchange> mapCurrencyExchanges = new HashMap<>();


    public CurrencyService(OpenRateService openRateService, ObjectMapper objectMapper) {
        this.openRateService = openRateService;
        this.objectMapper = objectMapper;

        updateCurrencyRates();
    }


    @Scheduled(cron = "${currency.update.cron}")
    private void periodicUpdate() {
        updateCurrencyRates();
    }

    public BaseMessageDto updateCurrencyRates() {
        BaseMessageDto dto = new BaseMessageDto();
        try {
            currencyExchanges.clear();
            mapCurrencyExchanges.clear();
            ZonedDateTime now = ZonedDateTime.now();
            String lastUpdate = Helper.formatDateTime(UPDATE_DATE_PATTERN, now);
            log.info("UPDATING ONLINE CURRENCY RATES AT " + lastUpdate);

            openRateService.initEuroRates(lastUpdate);

            CurrencyEnum[] currencyEnums = CurrencyEnum.values();
            int len = currencyEnums.length;
            for (int i = 0; i < len; i++) {
                CurrencyEnum baseCcy = currencyEnums[i];
                CurrencyUpdate currencyUpdate = openRateService.requestRates(baseCcy.name(), lastUpdate);
                CurrencyExchange currencyExchange = convert(currencyUpdate);
                this.currencyExchanges.add(currencyExchange);
                this.mapCurrencyExchanges.put(currencyExchange.getFrom().name(), currencyExchange);
            }
            saveToJsonFile(Helper.formatDateTime(Helper.FILE_PATTERN, now));

            log.info("Currency rates updated");
        } catch (Exception ex) {
            if (currencyExchanges == null || (currencyExchanges != null && currencyExchanges.size() == 0)) {
                loadFromJsonFile();
            }
            KoizaiExceptionHandler.handleException(dto, ex);
        }
        return dto;
    }

    private void saveToJsonFile(String lastUpdate) throws Exception {
        String dirPath = getFilesDirectory();
        String jsonPath = dirPath + SLASH + JSON_FILENAME + ".json";
        File jsonFile = new File(jsonPath);
        List oldList = null;
        if (jsonFile.exists()) {
            try {
                oldList = objectMapper.readValue(jsonFile, List.class);
            } catch (Exception ex) {
                oldList = this.currencyExchanges;
            }
        }

        try {
            objectMapper.writeValue(jsonFile, currencyExchanges);
            log.info(String.format("%s.json file saved", JSON_FILENAME));

            if (oldList != null) {
                String backupPath = dirPath + SLASH + String.format("%s-backup_%s", JSON_FILENAME, lastUpdate) + ".json";
                File backupFile = new File(backupPath);
                objectMapper.writeValue(backupFile, oldList);
                log.info(String.format("%s.json backup file created", JSON_FILENAME));
            }
        } catch (IOException e) {
            throw new KoizaiException(KoizaiException.PROCESS_FAILED, String.format("Fail saving %s because %s", JSON_FILENAME, e.getMessage()));
        }
    }

    private void loadFromJsonFile() {

        String jsonPath = getFilesDirectory() + SLASH + JSON_FILENAME + ".json";
        File jsonFile = new File(jsonPath);
        try {
            currencyExchanges = objectMapper.readValue(jsonFile, List.class);
            if (Helper.isValid(currencyExchanges)) {
                int size = currencyExchanges.size();
                for (int i = 0; i < size; i++) {
                    CurrencyExchange cx = currencyExchanges.get(i);
                    this.mapCurrencyExchanges.put(cx.getFrom().name(), cx);
                }
                log.info("successfully loaded from json");
            }
        } catch (IOException e) {
            log.error(String.format("Fail loading %s because %s", JSON_FILENAME, e.getMessage()));
        }
    }


    private String getFilesDirectory() {
        String dirPath = APP_HOME + SLASH + WEB_APPS + SLASH + KOIZAI_FOLDER;
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();

        }
        return dirPath;
    }

    private CurrencyExchange convert(CurrencyUpdate currencyUpdate) {
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.setFrom(CurrencyEnum.valueOf(currencyUpdate.getBase()));

        List<ExchangeRate> rates = new ArrayList<>();
        Map<CurrencyEnum, BigDecimal> mapRates = new HashMap<>();
        for (Map.Entry<String, BigDecimal> entry : currencyUpdate.getRates().entrySet()) {
            String key = entry.getKey();
            BigDecimal val = entry.getValue();
            try {
                CurrencyEnum ccyEnum = CurrencyEnum.valueOf(key);
                ExchangeRate exchangeRate = new ExchangeRate(ccyEnum, val);
                rates.add(exchangeRate);
                mapRates.put(ccyEnum, val);
            } catch (Exception ex) {
                continue;
            }

        }
        currencyExchange.setDate(currencyUpdate.getDate());
        currencyExchange.setExchangeRates(rates);
        return currencyExchange;
    }


    /**
     * Get currency rates by requesting to online history and passing date parameter
     *
     * @param baseCcy
     * @param date
     * @return
     */
    public CurrencyExchange findByDate(String baseCcy, String date) {
        CurrencyUpdate ccyUpdate = openRateService.requestRates(baseCcy, date);
        CurrencyExchange currencyExchange = convert(ccyUpdate);
        return currencyExchange;
    }


    public List<CurrencyExchange> findAll() {
        return currencyExchanges;
    }

    public CurrencyExchange findOne(String from) {
        return mapCurrencyExchanges.get(from);
    }
}
