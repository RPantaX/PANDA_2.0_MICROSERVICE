package com.panda.facturas.domain.ports.out;

import com.panda.facturas.domain.aggregates.response.ResponseSunat;

public interface RestSunatOut {
    ResponseSunat getInfoSunat(String ruc);
}
