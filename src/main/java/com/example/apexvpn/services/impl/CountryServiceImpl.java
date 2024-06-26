package com.example.apexvpn.services.impl;

import com.example.apexvpn.entities.Country;
import com.example.apexvpn.repositories.CountryRepository;
import com.example.apexvpn.responses.DataResponse;
import com.example.apexvpn.responses.OtherResponse;
import com.example.apexvpn.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;
    @Override
    public ResponseEntity<Object> getAllCountry() {
        List<Country> allCountry = countryRepository.findAll();

        return DataResponse.dataResponseBuilder(true, "OK", HttpStatus.OK, allCountry);
    }

    @Override
    public ResponseEntity<Object> getCountryById(Integer id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        if (optionalCountry.isEmpty()) {
            return OtherResponse.errorResponseBuilder(HttpStatus.NOT_FOUND, "Can't found country");
        }
        Country country = optionalCountry.get();
        return DataResponse.dataResponseBuilder(true, "OK", HttpStatus.OK, country);
    }

    @Override
    public ResponseEntity<Object> deleteCountryById(Integer id) {
        try {
            countryRepository.deleteById(id);
            return OtherResponse.successResponseBuilder(HttpStatus.OK, "Delete Country Successfully");
        } catch (EmptyResultDataAccessException e) {
            return OtherResponse.errorResponseBuilder(HttpStatus.NOT_FOUND, "Country not found with id: " + id);
        } catch (DataAccessException e) {
            return OtherResponse.errorResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while trying to delete the country");
        }
    }

    @Override
    public ResponseEntity<Object> addCountry(Country country) {
        try {
            Country savedCountry = countryRepository.save(country);
            return OtherResponse.successResponseBuilder(HttpStatus.CREATED, "Country added successfully with id: " + savedCountry.getId());
        } catch (DataAccessException e) {
            return OtherResponse.errorResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while trying to add the country");
        }
    }

    @Override
    public ResponseEntity<Object> getPremiumCountry(Boolean isPremium) {
        try {
            List<Country> lCountry = countryRepository.getCountryByPremium(isPremium);

            return DataResponse.dataResponseBuilder(true, "Query Successful", HttpStatus.OK, lCountry);
        } catch (DataAccessException e) {
            return OtherResponse.errorResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "An error when occurred when fetch data");
        }
    }
}