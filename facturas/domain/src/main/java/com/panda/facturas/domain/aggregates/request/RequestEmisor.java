package com.panda.facturas.domain.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEmisor {
    private String emisorRuc;
}
