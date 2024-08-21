package com.panda.facturas.domain.aggregates.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestEmisor {
    @NotBlank
    @Size(min = 11, max = 11, message = "El RUC del remitente debe tener exáctamente 11 caracteres.")
    private String emisorRuc;
}
