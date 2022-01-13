package com.poslog.stream.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Param;

@FeignClient(name="stores", url = "${CONSTANT.api.store.hostname}")
public interface StoreApi {
	@GetMapping(value = "/v1/stores")
	public String getStoresInfo(@RequestParam("page_size") int pageSize, @RequestParam("page_number") int pageNumber);
}
