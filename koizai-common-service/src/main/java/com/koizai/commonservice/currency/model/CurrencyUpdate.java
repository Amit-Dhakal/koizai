package com.koizai.commonservice.currency.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.TreeMap;

@Data
public class CurrencyUpdate implements Serializable {
    private String date;
    private String base;
    private TreeMap<String, BigDecimal> rates = new TreeMap<>();
    private TreeMap<String, BigDecimal> invertedRates = new TreeMap<>();


}
