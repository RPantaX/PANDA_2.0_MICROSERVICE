package com.panda.facturas.domain.aggregates.response;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListPaginableEmisor {
    List<EmisorDTO> emisorDTOList;
    private int numeroPagina;
    private int medidaPagina;
    private int totalPaginas;
    private long totalElementos;
    private boolean ultima;
}
