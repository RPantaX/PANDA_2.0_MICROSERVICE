package com.panda.facturas.domain.aggregates.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestEmisor {
    private String emisorRuc;
}
