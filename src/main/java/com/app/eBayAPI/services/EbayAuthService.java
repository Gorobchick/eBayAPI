package com.app.eBayAPI.services;

import org.springframework.stereotype.Service;

import com.ebay.api.client.auth.oauth2.OAuth2Api;
//import com.ebay.sdk.auth.oauth2.model.*;


@Service
public class EbayAuthService {

    private final OAuth2Api ebayOAuth2Api;

    public EbayAuthService(OAuth2Api ebayOAuth2Api) {
        this.ebayOAuth2Api = ebayOAuth2Api;
    }

    public String getAuthorizationUrl() {
        AuthorizationUrlRequest authorizationUrlRequest = new AuthorizationUrlRequest();
        authorizationUrlRequest.setScopes(ebayOAuth2Api.getConfig().getScope().split(","));
        return ebayOAuth2Api.generateAuthorizationUrl(authorizationUrlRequest);
    }

    public String fetchToken(String code) {
        FetchTokenRequest fetchTokenRequest = new FetchTokenRequest();
        fetchTokenRequest.setCode(code);
        fetchTokenRequest.setGrantType(GrantType.AUTHORIZATION_CODE);
        FetchTokenResponse fetchTokenResponse = ebayOAuth2Api.fetchToken(fetchTokenRequest);
        return fetchTokenResponse.getAccessToken();
    }
}
