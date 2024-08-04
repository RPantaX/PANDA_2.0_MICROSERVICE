package com.panda.facturas.application.controller;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.ports.in.FacturaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ms-factura/factura")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaServiceIn facturaService;
    @PostMapping("/crear")
    public ResponseEntity<FacturaDTO> registrarGuiaTransportista(@RequestBody RequestFactura requestFactura) {
        FacturaDTO facturaDTO = facturaService.crearFacturaIn(requestFactura);
        return new ResponseEntity<>(facturaDTO, HttpStatus.CREATED);
    }
}
