package com.panda.facturas.application.controller;

import com.panda.facturas.domain.aggregates.constants.Constants;
import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.aggregates.response.ResponseGuiaTransptByFactura;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableFactura;
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
    @GetMapping("/listar/paginable")
    public ResponseEntity<ResponseListPaginableFactura> listarPaginableFactura(@RequestParam(value = "pageNo", defaultValue = Constants.NUMERO_PAG_POR_DEFECTO, required = false) int numeroDePagina,
                                                                              @RequestParam(value = "pageSize", defaultValue = Constants.MEDIDA_PAG_POR_DEFECTO, required = false) int medidaDePagina,
                                                                              @RequestParam(value = "sortBy", defaultValue = Constants.ORDENAR_POR_DEFECTO_FACTURA, required = false) String ordenarPor,
                                                                              @RequestParam(value = "sortDir", defaultValue = Constants.ORDENAR_DIRECC_POR_DEFECTO, required = false) String sortDir) {
        ResponseListPaginableFactura responseListPaginableEmisor= facturaService.obtenerFacturasPaginableIn(
                numeroDePagina, medidaDePagina, ordenarPor, sortDir);
        return ResponseEntity.ok(responseListPaginableEmisor);
    }
}
