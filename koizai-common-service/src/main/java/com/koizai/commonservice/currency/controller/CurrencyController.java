package com.koizai.commonservice.currency.controller;


import com.koizai.commonservice.common.BaseController;
import com.koizai.commonservice.common.BaseMessageDto;
import com.koizai.commonservice.common.Constants;
import com.koizai.commonservice.common.Helper;
import com.koizai.commonservice.currency.model.CurrencyExchange;
import com.koizai.commonservice.currency.model.CurrencyListDto;
import com.koizai.commonservice.currency.model.ExchangeRate;
import com.koizai.commonservice.currency.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(description = "End point for currency exchange", tags = {"Currency"})
@RequestMapping("/currency")
public class CurrencyController extends BaseController implements Constants {
    private final CurrencyService currencyService;

    @GetMapping("/update")
    public ResponseEntity<BaseMessageDto> update() {
        log.info("updateCurrencyRates called by request");
        BaseMessageDto dto = currencyService.updateCurrencyRates();
        return doResponse(dto);
    }

    @GetMapping("/all")
    public List<CurrencyExchange> getAll() {

        return currencyService.findAll();
    }

    @GetMapping("/allWithDate")
    public ResponseEntity<CurrencyListDto> getAllWithDate() {
        CurrencyListDto dto = new CurrencyListDto();
        List<CurrencyExchange> list = currencyService.findAll();
        if (Helper.isValid(list)) {
            CurrencyExchange firstData = list.get(0);
            dto.setLastUpdated(firstData.getDate());
        }
        dto.setCurrencyRates(list);
        return doResponse(dto);
    }

    @GetMapping("/{from}")
    @ApiOperation(value = "Get currency exchange using currency enum")
    public CurrencyExchange getCurrencyExchangeFrom(@PathVariable("from") String from) {
        CurrencyExchange currencyExchange = currencyService.findOne(from);
        return currencyExchange;
    }

    @GetMapping("/{from}/rates")
    @ApiOperation(value = "Get currency exchange rates by passing 'from' parameter as base currency")
    public List<ExchangeRate> getRatesFrom(@PathVariable("from") String from) {
        CurrencyExchange currencyExchange = currencyService.findOne(from);
        List<ExchangeRate> result = currencyExchange.getExchangeRates();
        return result;
    }

    @GetMapping("/{from}/rates/{date}")
    @ApiOperation(value = "Get currency exchange rates history by including 'from' parameter as base currency and on which date (yyyy-MM-dd) will be used")
    public List<ExchangeRate> getRatesFromAndDate(@PathVariable("from") String from, @PathVariable("date") String date) {
        CurrencyExchange currencyExchange = currencyService.findByDate(from, date);
        List<ExchangeRate> result = currencyExchange.getExchangeRates();
        return result;
    }


}
