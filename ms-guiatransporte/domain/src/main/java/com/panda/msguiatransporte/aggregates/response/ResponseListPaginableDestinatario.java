package com.panda.msguiatransporte.aggregates.response;

import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseListPaginableDestinatario {
    private List<DestinatarioDTO> destinatarioDTOList;
    private int numeroPagina;
    private int medidaPagina;
    private int totalPaginas;
    private long totalElementos;
    private boolean ultima;
}
