package com.koizai.commonservice.currency.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class CurrencyExchange implements Serializable {
    @ApiModelProperty(required = true)
    private CurrencyEnum from;
    private List<ExchangeRate> exchangeRates = new ArrayList<>();
    private String date;


}
