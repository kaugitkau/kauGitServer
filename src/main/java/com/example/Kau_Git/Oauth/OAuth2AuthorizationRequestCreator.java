package com.example.Kau_Git.Oauth;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OAuth2AuthorizationRequestCreator {

    public static OAuth2AuthorizationRequest createAuthorizationRequest(String clientId, String redirectUri, String authorizationUri, Set<String> scopes) {
        Map<String, Object> additionalParameters = new HashMap<>();
        additionalParameters.put(OAuth2ParameterNames.CLIENT_ID, clientId);
        additionalParameters.put(OAuth2ParameterNames.REDIRECT_URI, redirectUri);
        additionalParameters.put(OAuth2ParameterNames.RESPONSE_TYPE, "code");
        additionalParameters.put(OAuth2ParameterNames.SCOPE, String.join(" ", scopes));

        return OAuth2AuthorizationRequest.authorizationCode()
                .authorizationUri(authorizationUri)
                .clientId(clientId)
                .redirectUri(redirectUri)
                .scopes(scopes)
                .state("custom-state") // 필요에 따라 상태값을 설정
                .additionalParameters(additionalParameters)
                .build();
    }
}
