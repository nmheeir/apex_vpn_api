package com.example.apexvpn.repositories;

import com.example.apexvpn.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("SELECT v FROM Country v WHERE v.premium = :isPremium")
    List<Country> getCountryByPremium(@Param("isPremium") Boolean isPremium);
}
