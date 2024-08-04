package com.panda.facturas.domain.aggregates.response;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.dto.FacturaDetalleDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseGuiaTransptByFactura {
    private FacturaDTO facturaDTO;
    private List<FacturaDetalleDTO> facturaDetalleDTOList;
    private List<ResponseGuiaTranspt> guiaTranspts;
}
