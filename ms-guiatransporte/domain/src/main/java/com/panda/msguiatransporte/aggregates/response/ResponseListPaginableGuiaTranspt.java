package com.panda.msguiatransporte.aggregates.response;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseListPaginableGuiaTranspt {
    List<GuiaTransptDTO> list;
    private int numeroPagina;
    private int medidaPagina;
    private int totalPaginas;
    private long totalElementos;
    private boolean ultima;
}
