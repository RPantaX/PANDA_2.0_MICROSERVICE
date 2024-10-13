package com.panda.facturas.domain.aggregates.response;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseListPaginableEmisor {
    List<EmisorDTO> emisorDTOList;
    private int numeroPagina;
    private int medidaPagina;
    private int totalPaginas;
    private long totalElementos;
    private boolean ultima;
}
