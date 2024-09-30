package com.panda.facturas.domain.aggregates.response;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.dto.FacturaDetalleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseListPaginableFactura {
    private List<FacturaDTO> facturaDetallesList;
    private int numeroPagina;
    private int medidaPagina;
    private int totalPaginas;
    private long totalElementos;
    private boolean ultima;
}
