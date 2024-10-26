package com.koizai.commonservice.inflation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InflationRate {
    private String countryCode;
    private Integer imfCountryCode;
    private String country;
    private Integer year;
    private BigDecimal inflationRate;

}