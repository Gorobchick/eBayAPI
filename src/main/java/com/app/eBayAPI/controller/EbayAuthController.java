package com.app.eBayAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.eBayAPI.services.EbayAuthService;

@RestController
public class EbayAuthController {

    private final EbayAuthService ebayAuthService;

    String Token = this.handleCallback(getAuthorizationUrl());

    public EbayAuthController(EbayAuthService ebayAuthService) {
        this.ebayAuthService = ebayAuthService;
    }

    // public EbayAuthController(String Token){
    //     Token = this.Token;
    // }

    @GetMapping("/ebay/authorize")
    public String getAuthorizationUrl() {
        return ebayAuthService.getAuthorizationUrl();
    }

    @GetMapping("/ebay/callback")
    public String handleCallback(@RequestParam("sessionId") String sessionId) {
        String accessToken = ebayAuthService.fetchToken(sessionId);
        // Використовуйте accessToken для взаємодії з API eBay

        return accessToken;
    }

    
}
