package com.ishortner.url.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("services/server")
public interface UrlFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/servers")
    void insertServer(Long id);
}