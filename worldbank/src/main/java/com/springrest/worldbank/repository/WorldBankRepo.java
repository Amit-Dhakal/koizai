package com.springrest.worldbank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.springrest.worldbank.model.WorldBankData;
import java.util.List;

@Repository
public interface WorldBankRepo extends MongoRepository<WorldBankData,String>{

    @Query("{$and:[{country:?0},{year:?1}] }")
    List<WorldBankData> getAllByCountryAndYear(String country, String year);

}
