package com.panda.msguiatransporte.aggregates.response;

import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseListPaginableRemitente {
    List<RemitenteDTO> list;
    private int numeroPagina;
    private int medidaPagina;
    private int totalPaginas;
    private long totalElementos;
    private boolean ultima;
}
