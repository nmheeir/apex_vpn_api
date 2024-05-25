package com.example.apexvpn.repositories;

import com.example.apexvpn.entities.PaymentRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PaymentRepository extends JpaRepository<PaymentRecord, Integer> {
    PaymentRecord findByTxnRef(String txnRef);
}
