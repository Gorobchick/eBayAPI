package com.app.eBayAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ebay.sdk.auth.oauth2.*;

@Configuration
public class EbayOAuthClientConfig {

    @Value("$Oleksand-Spring-SBX-1077ff297-bcbf4faa")
    private String clientId;

    @Value("SBX-077ff297058a-f9df-4e1a-8203-f0e5")
    private String clientSecret;

    @Value("Oleksandr")
    private String Name;

    @Bean
    public OAuth2Config ebayOAuth2Config() {
        OAuth2Credential credential = new OAuth2Credential(clientId, clientSecret, Name);
        return new OAuth2Config(credential);
    }

    @Bean
    public OAuth2Api ebayOAuth2Api(OAuth2Config ebayOAuth2Config) {
        return new OAuth2ApiImpl(ebayOAuth2Config);
    }
}

