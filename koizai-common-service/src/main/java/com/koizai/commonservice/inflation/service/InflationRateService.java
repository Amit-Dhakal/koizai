package com.koizai.commonservice.inflation.service;

import java.util.List;

import com.koizai.commonservice.inflation.model.InflationRate;

/**
 * An interface for infalation rate service
 *
 * @author rajukc@koizai.com
 */
public interface InflationRateService {
    InflationRate findByCountryCodeAndYear(String countryCode, int year);

    List<InflationRate> findByCountryCode(String countryCode);

    void processFile() throws Exception;

}