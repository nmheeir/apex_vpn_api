package com.example.apexvpn.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class DataResponse {

    public static ResponseEntity<Object> dataResponseBuilder(
            Boolean isSuccessful,
            String message,
            HttpStatus httpStatus,
            Object responseObject
    ) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("isSuccessful", isSuccessful);
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", responseObject);

        return new ResponseEntity<>(response, httpStatus);
    }
}
