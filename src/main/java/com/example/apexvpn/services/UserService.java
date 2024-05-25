package com.example.apexvpn.services;


import com.example.apexvpn.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User fetchData(String accessToken);

    ResponseEntity<Object> deleteOwner(String token);

    ResponseEntity<Object> resetPassword(String email, String password);

}
