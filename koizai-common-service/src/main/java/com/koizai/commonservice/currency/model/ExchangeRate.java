package com.koizai.commonservice.currency.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ExchangeRate {
    @ApiModelProperty(required = true)
    private CurrencyEnum to;
    private BigDecimal rate = BigDecimal.ONE;
}
