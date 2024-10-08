package com.panda.facturas.domain.impl;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.aggregates.response.ResponseGuiaTransptByFactura;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableFactura;
import com.panda.facturas.domain.ports.in.FacturaServiceIn;
import com.panda.facturas.domain.ports.out.FacturaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaServiceIn {
    private final FacturaServiceOut facturaServiceOut;

    @Override
    public FacturaDTO crearFacturaIn(RequestFactura requestFactura) {

        return facturaServiceOut.crearFacturaOut(requestFactura);
    }

    @Override
    public Optional<ResponseGuiaTransptByFactura> buscarFacturaPorfacturaSerienumeroIn(String facturaSerienumero) {
        return facturaServiceOut.buscarFacturaPorfacturaSerienumeroOut(facturaSerienumero);
    }

    @Override
    public List<FacturaDTO> obtenerFacturasIn() {
        return facturaServiceOut.obtenerFacturasOut();
    }

    @Override
    public ResponseListPaginableFactura obtenerFacturasPaginableIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        return facturaServiceOut.obtenerFacturasPaginableOut(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }
}
