package com.example.apexvpn.services.impl;

import com.example.apexvpn.entities.User;
import com.example.apexvpn.repositories.PaymentRepository;
import com.example.apexvpn.services.PaymentService;
import com.example.apexvpn.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public String getPremiumKey(User user, Integer time) {
        return jwtUtils.generatePremiumKey(user.getUsername(), time);
    }
}
