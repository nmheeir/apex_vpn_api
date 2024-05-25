package com.example.apexvpn.services;


import com.example.apexvpn.entities.User;

public interface PaymentService {

    String getPremiumKey(User user, Integer time);

}
