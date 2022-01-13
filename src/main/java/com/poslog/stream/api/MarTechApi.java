package com.poslog.stream.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import com.poslog.stream.api.config.MarTechApiClientConfig;
import com.poslog.stream.api.model.TriggerTansactionEmailRequest;

@FeignClient(name="martech", url = "${CONSTANT.api.token_url}", configuration = MarTechApiClientConfig.class)
public interface MarTechApi {
	@PostMapping(value = "${CONSTANT.api.martech.triggerTransactional.path}", 
			     consumes = MediaType.APPLICATION_JSON_VALUE)
	public String triggerTransactionalEmail(TriggerTansactionEmailRequest requestBody);
}
