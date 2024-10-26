package com.springrest.worldbank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.worldbank.model.WorldBankData;
import com.springrest.worldbank.repository.WorldBankRepo;

import java.util.List;
@Service
public class WorldBankServiceImpl implements WorldBankService {

    @Autowired
    WorldBankRepo worldBankRepo;

    @Override
    public List<WorldBankData> getAllByCountryAndYear(String country, String year) {

        List<WorldBankData> listworldbankdata = worldBankRepo.getAllByCountryAndYear(country,year);

return listworldbankdata;

    }
    
    
    
    
    
    

}

