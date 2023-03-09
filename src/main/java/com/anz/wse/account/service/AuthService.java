package com.anz.wse.account.service;

import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
public class AuthService {
    /*
    This dummy service assume to connect with Authentication service provider and extract user id from the service
     */
    public int getUserIdFromAuthToken(String authToken) throws AuthenticationException {
        String[] tokenParts = authToken.split(":");

        if(tokenParts.length != 2) {
            throw new AuthenticationException("Authentication token mismatch");
        }

        int userId;
        try {
            userId = Integer.parseInt(tokenParts[1]);
        } catch (NumberFormatException e) {
            throw new AuthenticationException("User id format mismatch");
        }
        return userId;
    }
}
