package com.springrest.worldbank.service;

import java.util.List;

import com.springrest.worldbank.model.WorldBankData;

public interface WorldBankService {

    List<WorldBankData> getAllByCountryAndYear(String country, String year);

}
