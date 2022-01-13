package com.poslog.stream.api.config;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.poslog.stream.api.ApigeeOAuth2Api;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@PropertySource("classpath:bootstrap.properties")
public class MarTechApiClientConfig {
	@Value("${spring.security.oauth2.client.registration.martech.client-id}")
    private String client_id;
	
	@Value("${spring.security.oauth2.client.registration.martech.client-secret}")
    private String client_secret;
	
	@Value("${spring.security.oauth2.client.registration.martech.authorization-grant-type}")
    private String grant_type;
	
	@Value("${CONSTANT.api.grant_type}")
    private String CONSTANT_GRANT_TYPE;
	
	@Value("${CONSTANT.api.x-apikey}")
    private String CONSTANT_X_APIKEY;
	
	@Value("${CONSTANT.api.client_id}")
    private String CONSTANT_CLIENT_ID;
	
	@Value("${CONSTANT.api.client_secret}")
    private String CONSTANT_CLIENT_SECRET;
	
	@Value("${CONSTANT.api.Authorization}")
    private String CONSTANT_AUTHORIZATION;
	
	@Value("${CONSTANT.api.access_token}")
    private String CONSTANT_ACCESS_TOKEN;
	
	@Autowired
	private ApigeeOAuth2Api apigeeOAuth2Api;
	
	@Bean
    public RequestInterceptor requestTokenBearerInterceptor() {

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
            	String apiKey = client_id;
            	
            	MultiValueMap<String, Object> reqeustHeader = new LinkedMultiValueMap<>();
            	reqeustHeader.add(CONSTANT_GRANT_TYPE, grant_type);
            	reqeustHeader.add(CONSTANT_X_APIKEY, apiKey);
            	
            	MultiValueMap<String, Object> reqeustBody = new LinkedMultiValueMap<>();
            	reqeustBody.add(CONSTANT_CLIENT_ID, client_id);
            	reqeustBody.add(CONSTANT_CLIENT_SECRET, client_secret);
            	
            	String oauthResonse = apigeeOAuth2Api.getAccessToken(reqeustHeader,reqeustBody);
            	
            	JSONObject jsonObject = new JSONObject(oauthResonse);
            	String token = "Bearer "+ jsonObject.getString(CONSTANT_ACCESS_TOKEN);
            	System.out.println("token ->" + token);

                requestTemplate.header(CONSTANT_X_APIKEY, apiKey);
                requestTemplate.header(CONSTANT_AUTHORIZATION, token);
            }
        };
    }
}
