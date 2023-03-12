package com.anz.wse.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    /*
    This dummy service assume to connect with Authentication service provider and extract user id from the service
     */
    public int getUserIdFromAuthToken(String authToken) {
        String[] tokenParts = authToken.split(":");

        if (tokenParts.length != 2) {
            log.error("message=\"Authentication token mismatch\", error=\"ERROR 5145\"");
            throw new IllegalArgumentException("Authentication token mismatch, ERROR 5145");
        }

        int userId = -1;
        try {
            userId = Integer.parseInt(tokenParts[1]);
        } catch (NumberFormatException e) {
            log.error("message=\"Authentication token mismatch\", error=\"ERROR 5632\"");
            throw new IllegalArgumentException("Authentication token mismatch, ERROR 5632");
        }

        return userId;
    }
}
