package com.panda.facturas.infraestructure.rest.client;

import com.panda.facturas.domain.aggregates.response.ResponseGuiaTransptByFactura;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "MS-GUIA-TRANSPORTISTA")
public interface ClientGuiaTransportista {
    @GetMapping("/ms-transportista/v1/guiatransportista/todos")
    List<ResponseGuiaTransptByFactura> getGuiaTransportistas();
    @PostMapping("/ms-transportista/v1/guiatransportista/{id}")
    ResponseGuiaTransptByFactura addFacturaToGuiaTransp(@PathVariable Long id, @RequestBody String facturaId);
}
