package com.koizai.commonservice.currency.model;


import com.koizai.commonservice.common.BaseMessageDto;
import lombok.Data;

import java.util.List;

@Data
public class CurrencyListDto extends BaseMessageDto {
    private String lastUpdated;
    private List<CurrencyExchange> currencyRates;
}
