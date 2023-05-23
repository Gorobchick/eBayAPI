package com.app.eBayAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ebay.api.client.auth.oauth2.OAuth2Api;

@Configuration
public class EbayOAuthClientConfig {

    private static final String CLIENT_ID = "Oleksand-Spring-SBX-1077ff297-bcbf4faa";
    private static final String CLIENT_SECRET = "SBX-077ff297058a-f9df-4e1a-8203-f0e5";
    private static final String NAME = "Oleksander";

    @Bean
    public OAuth2Api ebayOAuth2ApiInterface() {
        return new OAuth2ApiInterface();
    }

    @Bean
    public OAuth2Api oauth2Api(OAuth2ApiInterface ebayOAuth2ApiInterface) {
        OAuth2Credential credential = new OAuth2Credential(CLIENT_ID, CLIENT_SECRET, NAME);
        return new OAuth2Api(credential, ebayOAuth2ApiInterface);
    }
}
