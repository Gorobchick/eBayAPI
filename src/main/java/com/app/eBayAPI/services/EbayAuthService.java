package com.app.eBayAPI.services;

import org.springframework.stereotype.Service;
import com.ebay.api.client.auth.oauth2.OAuth2Api;

@Service
public class EbayAuthService {

    private final OAuth2Api oauth2Api;

    public EbayAuthService(OAuth2Api oauth2Api) {
        this.oauth2Api = oauth2Api;
    }

    public String getAuthorizationUrl() {
        GetSessionIdRequest sessionIdRequest = new GetSessionIdRequest();
        sessionIdRequest.setRuName(oauth2Api.getCredential().getRuName());

        GetSessionIdResponse sessionIdResponse = oauth2Api.getSessionId(sessionIdRequest);

        if (sessionIdResponse != null && sessionIdResponse.getErrors() == null) {
            return oauth2Api.getAuthorizationUrl(sessionIdResponse.getSessionId());
        }

        // Обробка помилок

        return null;
    }

    public String fetchToken(String sessionId) {
        FetchTokenRequest fetchTokenRequest = new FetchTokenRequest();
        fetchTokenRequest.setSessionId(sessionId);

        FetchTokenResponse fetchTokenResponse = oauth2Api.fetchToken(fetchTokenRequest);

        if (fetchTokenResponse != null && fetchTokenResponse.getErrors() == null) {
            return fetchTokenResponse.getAccessToken();
        }

        // Обробка помилок

        return null;
    }
}
