package com.ishortner.server.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ishortner.server.web.rest.ServerResource;

@FeignClient(name = "url", fallback= ServerResource.class)
public interface ServerFeignClient {

	
}
