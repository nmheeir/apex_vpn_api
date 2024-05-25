package com.example.apexvpn.services;

import com.example.apexvpn.entities.Country;
import org.springframework.http.ResponseEntity;

public interface CountryService {
    ResponseEntity<Object> getAllCountry();
    ResponseEntity<Object> getCountryById(Integer id);

    ResponseEntity<Object> deleteCountryById(Integer id);

    ResponseEntity<Object> addCountry(Country country);

    ResponseEntity<Object> getPremiumCountry(Boolean isPremium);
}
