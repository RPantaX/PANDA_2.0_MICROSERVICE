package com.panda.facturas.domain.ports.out;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.aggregates.response.ResponseGuiaTransptByFactura;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableFactura;

import java.util.List;
import java.util.Optional;

public interface FacturaServiceOut {

    FacturaDTO crearFacturaOut (RequestFactura requestFactura);

    Optional<ResponseGuiaTransptByFactura> buscarFacturaPorfacturaSerienumeroOut (String facturaSerienumero);

    List<FacturaDTO> obtenerFacturasOut ();
    ResponseListPaginableFactura obtenerFacturasPaginableOut (int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

}
