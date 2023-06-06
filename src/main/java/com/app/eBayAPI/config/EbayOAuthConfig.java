package com.app.eBayAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ebay.api.client.auth.oauth2.OAuth2Api;
//import com.ebay.sdk.auth.oauth2.*;



@Configuration
public class EbayOAuthConfig {

    @Value("Oleksand-Spring-SBX-1077ff297-bcbf4faa")
    private String clientId;

    @Value("SBX-077ff297058a-f9df-4e1a-8203-f0e5")
    private String clientSecret;

    @Value("${ebay.oauth.redirectUri}")
    private String redirectUri;

    @Value("${ebay.oauth.scope}")
    private String scope;

    @Value("${ebay.oauth.environment}")
    private String environment;

    @Bean
    public OAuth2Api ebayOAuth2Api() {
        OAuth2Api oauth2Config = new OAuth2Api(clientId, clientSecret, redirectUri, scope, environment);
        return new OAuth2Api(oauth2Config);
    }
}


