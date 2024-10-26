package com.koizai.commonservice.currency.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.koizai.commonservice.common.KoizaiRuntimeException;
import com.koizai.commonservice.currency.model.CurrencyUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;


/**
 * @author anton.nuansantio@devstack.com.au
 */

@Service
@Slf4j

public class OpenRateService {

    private final String baseUrl = "http://api.exchangeratesapi.io/v1/latest";
    private String ACCESS_KEY = "fcdabbef230ba054a7cebec2ec0acf0d";
    //    private String SYMBOLS = "AUD,USD,MYR,SGD,HKD,NZD,GBP,EUR";
    private final ObjectMapper objectMapper;
    private final RestApiService apiService;
    private long connectionTimeout = 20000;
    private long readTimeout = 10000;

    private Map<String, CurrencyUpdate> mapBase = new HashMap<>();
    private CurrencyUpdate euroCurrencyUpdate;


    public OpenRateService(ObjectMapper objectMapper, RestApiService apiService) {
        this.objectMapper = objectMapper;
        this.apiService = apiService;


    }

    @Async("koizaiAsynch")
    public CompletableFuture<CurrencyUpdate> asynchRequestRates(String baseCcy, String strNow) {
        return CompletableFuture.completedFuture(requestRates(baseCcy, strNow));
    }


    public void initEuroRates(String strNow) {
        String url = String.format("%s?access_key=%s&format=1", baseUrl, ACCESS_KEY);
        log.info("calling : " + url + " at " + strNow);
        try {
            String responseBody = apiService.doGet(url);
            euroCurrencyUpdate = objectMapper.readValue(responseBody, CurrencyUpdate.class);
            euroCurrencyUpdate.setDate(strNow);

            if (euroCurrencyUpdate != null && euroCurrencyUpdate.getRates() != null && !euroCurrencyUpdate.getRates().isEmpty()) {
                TreeMap<String, BigDecimal> mapInvertedRates = new TreeMap<>();
                for (Map.Entry<String, BigDecimal> ccyAndRate : euroCurrencyUpdate.getRates().entrySet()) {
                    String ccy = ccyAndRate.getKey();
                    BigDecimal rate = ccyAndRate.getValue();
                    BigDecimal invertedRate = BigDecimal.ONE.divide(rate, 20, BigDecimal.ROUND_HALF_DOWN);
                    mapInvertedRates.put(ccy, invertedRate);

                }
                euroCurrencyUpdate.setInvertedRates(mapInvertedRates);
            }

        } catch (Exception ex) {
            log.error(String.format("EURO is not available because %s", ex.getMessage()));
        }

    }

    public CurrencyUpdate requestRates(String baseCcy, String strNow) {
        if(this.euroCurrencyUpdate==null){
            throw new KoizaiRuntimeException(KoizaiRuntimeException.DATA_NOT_FOUND, "Could not request rates when EURO was not available");
        }
        CurrencyUpdate ccyUpdate = new CurrencyUpdate();
        ccyUpdate.setDate(strNow);
        ccyUpdate.setBase(baseCcy);
        for (String ccy : euroCurrencyUpdate.getRates().keySet()) {
            if (baseCcy.equals(ccy)) {
                ccyUpdate.getRates().put(ccy, BigDecimal.ONE);
            } else {
                try {
                    BigDecimal baseCcyInEuro = euroCurrencyUpdate.getInvertedRates().get(baseCcy);
                    BigDecimal ccyValue = baseCcyInEuro.multiply(euroCurrencyUpdate.getRates().get(ccy)).setScale(10, BigDecimal.ROUND_HALF_UP);
                    ccyUpdate.getRates().put(ccy, ccyValue);
                }catch (Exception ex){
                    log.error(String.format("%s is bad currency because %s ", baseCcy, ex.getMessage()));
                }
            }
        }

        return ccyUpdate;

    }


}
