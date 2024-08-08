package com.panda.facturas.infraestructure.rest.msclient;

import com.panda.facturas.domain.aggregates.response.ResponseGuiaTranspt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MS-GUIAS-TRANSPORTISTA")
public interface ClientMSGuiaTranspt {
    @PutMapping("/v1/ms-guia-transportista/guiaTransportista/referenciarFactura")
    ResponseGuiaTranspt referenciarFactura(@RequestParam(value = "guiaTransptSerieNumero",required = true) String guiaTransptSerieNumero,
                                                  @RequestParam(value = "facturaSerieNumero",required = true) String facturaSerieNumero);
    @GetMapping("/v1/ms-guia-transportista/guiaTransportista/buscarPorFacturaSerieNumero/{facturaSerieNumero}")
    List<ResponseGuiaTranspt> ListarGuiasPorFacturaSerieNumero(@PathVariable("facturaSerieNumero") String facturaSerieNumero);

    @GetMapping("/v1/ms-guia-transportista/guiaTransportista/listarPorGuiaYSerie")
    ResponseGuiaTranspt listarGuiaTransportistaPorGuiaYSerie(@RequestParam(value = "guiaNumero", defaultValue = "1", required = true) long guiaNumero,
                                                             @RequestParam(value = "guiaSerie", defaultValue = "EG03", required = true) String guiaSerie);
}
