package com.panda.facturas.domain.ports.in;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;

import java.util.List;
import java.util.Optional;

public interface FacturaServiceIn {

    FacturaDTO crearFacturaIn (RequestFactura requestFactura);

    Optional<FacturaDTO> buscarFacturaPorfacturaSerienumeroIn (String facturaSerienumero);

    List<FacturaDTO> obtenerFacturasIn ();
}
