package com.panda.facturas.infraestructure.rest.client;

import com.panda.facturas.domain.aggregates.response.ResponseGuiaTransportista;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "MS-GUIA-TRANSPORTISTA")
public interface ClientGuiaTransportista {
    @GetMapping("/ms-transportista/v1/guiatransportista/todos")
    List<ResponseGuiaTransportista> getGuiaTransportistas();
    @PostMapping("/ms-transportista/v1/guiatransportista/{id}")
    ResponseGuiaTransportista addFacturaToGuiaTransp(@PathVariable Long id, @RequestBody String facturaId);
}
