package com.panda.facturas.infraestructure.rest;

import com.panda.facturas.domain.aggregates.response.ResponseSunat;
import com.panda.facturas.domain.ports.out.RestSunatOut;
import com.panda.facturas.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestSunatAdapter implements RestSunatOut {
    private final ClienteSunat sunat;
    @Value("${token.api}")
    private String tokenApi;
    @Override
    public ResponseSunat getInfoSunat(String ruc) {
        String authorization = "Bearer " + tokenApi;
        return sunat.getInfoSunat( ruc, authorization);
    }
}
