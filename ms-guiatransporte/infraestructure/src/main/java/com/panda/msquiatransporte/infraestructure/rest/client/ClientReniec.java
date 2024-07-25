package com.panda.msquiatransporte.infraestructure.rest.client;

import com.panda.msguiatransporte.aggregates.response.ResponseReniec;
import com.panda.msquiatransporte.infraestructure.rest.errors.ReniecError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-reniec", url = "https://api.apis.net.pe/v2/reniec/", configuration = ReniecError.class)
public interface ClientReniec {
    @GetMapping("/dni")
    ResponseReniec getInfoReniec(@RequestParam("numero") String numero,
                                 @RequestHeader("Authorization") String authorizationHeader);
}
