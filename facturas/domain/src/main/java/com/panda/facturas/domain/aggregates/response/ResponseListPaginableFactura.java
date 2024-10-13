package com.panda.facturas.domain.aggregates.response;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.dto.FacturaDetalleDTO;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResponseListPaginableFactura {
    private List<FacturaDTO> facturaDetallesList;
    private int numeroPagina;
    private int medidaPagina;
    private int totalPaginas;
    private long totalElementos;
    private boolean ultima;
}
