package com.app.eBayAPI.services;

import com.ebay.sdk.auth.oauth2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EbayAuthService {

    private final OAuth2Api ebayOAuth2Api;

    @Autowired
    public EbayAuthService(OAuth2Api ebayOAuth2Api) {
        this.ebayOAuth2Api = ebayOAuth2Api;
    }

    public String getAuthorizationUrl() {
        GetSessionIdRequest sessionIdRequest = new GetSessionIdRequest();
        sessionIdRequest.setRuName(ebayOAuth2Api.getCredential().getRuName());

        GetSessionIdResponse sessionIdResponse = ebayOAuth2Api.getSessionId(sessionIdRequest);

        if (sessionIdResponse != null && sessionIdResponse.getErrors() == null) {
            return ebayOAuth2Api.getAuthorizationUrl(sessionIdResponse.getSessionId());
        }

        // Обробка помилок

        return null;
    }

    public String fetchToken(String sessionId) {
        FetchTokenRequest fetchTokenRequest = new FetchTokenRequest();
        fetchTokenRequest.setSessionId(sessionId);

        FetchTokenResponse fetchTokenResponse = ebayOAuth2Api.fetchToken(fetchTokenRequest);

        if (fetchTokenResponse != null && fetchTokenResponse.getErrors() == null) {
            return fetchTokenResponse.getAccessToken();
        }

        // Обробка помилок

        return null;
    }
}
