package com.panda.facturas.domain.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestFactura {
    private Integer id;
    private String clienteRuc;

    private String observacion;
    private String seguieGuia;
    private int numeroGuia;
    private Long idUser;
    //private List<Item> items;
}
