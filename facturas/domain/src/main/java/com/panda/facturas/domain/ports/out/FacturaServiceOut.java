package com.panda.facturas.domain.ports.out;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;

import java.util.List;
import java.util.Optional;

public interface FacturaServiceOut {

    FacturaDTO crearFacturaOut (RequestFactura requestFactura);

    Optional<FacturaDTO> buscarFacturaPorfacturaSerienumeroOut (String facturaSerienumero);

    List<FacturaDTO> obtenerFacturasOut ();
}
