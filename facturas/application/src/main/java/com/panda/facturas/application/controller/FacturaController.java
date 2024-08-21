package com.panda.facturas.application.controller;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.aggregates.response.ResponseGuiaTransptByFactura;
import com.panda.facturas.domain.ports.in.FacturaServiceIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ms-factura/factura")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaServiceIn facturaService;
    @PostMapping("/crear")
    public ResponseEntity<FacturaDTO> registrarGuiaTransportista(@RequestBody @Valid RequestFactura requestFactura) {
        FacturaDTO facturaDTO = facturaService.crearFacturaIn(requestFactura);
        return new ResponseEntity<>(facturaDTO, HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<FacturaDTO>> listarFacturas() {
        return new ResponseEntity<>(facturaService.obtenerFacturasIn(), HttpStatus.OK);
    }

    @GetMapping("/listar/{facturaSerieNumero}")
    public ResponseEntity<ResponseGuiaTransptByFactura> listarFactura(@PathVariable("facturaSerieNumero") String facturaSerieNumero) {
        Optional<ResponseGuiaTransptByFactura> facturaOptional = facturaService.buscarFacturaPorfacturaSerienumeroIn(facturaSerieNumero);
        return new ResponseEntity<>(facturaOptional.get(), HttpStatus.OK);
    }

}
