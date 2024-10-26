package com.koizai.commonservice.inflation.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InflationRateEnum {
    COUNTRY_CODE("Country Code"), 
    IMF_COUNTRY_CODE("IMF Country Code"), 
    COUNTRY("Country"), 
    INDICATOR_TYPE("Indicator Type"), 
    SERIES_NAME("Series Name"),
    YEAR("2015"), 
    RATE("Rate");

    private final String displayName;
}
