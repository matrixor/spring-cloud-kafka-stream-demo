package com.poslog.stream.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;

@PropertySource("classpath:bootstrap.properties")
@FeignClient(name="oauth2", url = "${CONSTANT.api.token_url}")
public interface ApigeeOAuth2Api {
	@Headers("${CONSTANT.api.token_header.content_type}")
	@PostMapping(value = "${CONSTANT.api.token_path}", 
				 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String getAccessToken(@RequestHeader MultiValueMap<String, Object> requestHeader,
								 @RequestBody MultiValueMap<String, Object> requestBody);
}
