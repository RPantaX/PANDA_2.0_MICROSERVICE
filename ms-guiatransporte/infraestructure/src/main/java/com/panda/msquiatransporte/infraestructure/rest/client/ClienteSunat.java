package com.panda.msquiatransporte.infraestructure.rest.client;

import com.panda.msguiatransporte.aggregates.response.ResponseSunat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-sunat", url = "https://api.apis.net.pe/v2/sunat/")
public interface ClienteSunat   {
    @GetMapping("/ruc")
    ResponseSunat getInfoSunat(@RequestParam("numero") String numero,
                               @RequestParam("Authorization") String authorizationHeader);
}
