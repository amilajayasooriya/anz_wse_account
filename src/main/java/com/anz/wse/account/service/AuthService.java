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
            log.error("Authentication token mismatch");
            throw new IllegalArgumentException("Authentication token mismatch");
        }

        return Integer.parseInt(tokenParts[1]);
    }
}
