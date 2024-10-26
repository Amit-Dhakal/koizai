package com.koizai.commonservice.inflation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.*;

import com.koizai.commonservice.inflation.model.InflationRate;
import com.koizai.commonservice.inflation.service.InflationRateService;
import com.neovisionaries.i18n.CountryCode;

/**
 * A controller class for InflationRate
 *
 * @author rajukc@koizai.com
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/inflation")
@Api(value = "End point for inflation", tags = { "Inflation" })
public class InflationRateController {

    private final InflationRateService inflationRateService;

    @GetMapping
    @ApiOperation(value = "Get Inflation By CountryCode and Year")
    public InflationRate get(@RequestParam(value = "countryCode", required = true) String countryCode,
            @RequestParam(value = "year", required = true) int year) {

        for (CountryCode code : CountryCode.values()) {
            if (code.getAlpha2().equals(countryCode)) {
                return inflationRateService.findByCountryCodeAndYear(code.getAlpha3(), year);
            }
        }

        return null;
    }

    @GetMapping("/country")
    @ApiOperation(value = "Get Inflation By CountryCode")
    public List<InflationRate> getByCountryCode(@RequestParam(value = "countryCode", required = true) String countryCode) {

        for (CountryCode code : CountryCode.values()) {
            if (code.getAlpha2().equals(countryCode)) {
                return inflationRateService.findByCountryCode(code.getAlpha3());
            }
        }
        return null;

    }

    @PostConstruct
    public void process() throws Exception {
        inflationRateService.processFile();
    }

}
