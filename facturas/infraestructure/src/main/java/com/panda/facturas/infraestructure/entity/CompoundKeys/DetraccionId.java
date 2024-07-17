package com.panda.facturas.infraestructure.entity.CompoundKeys;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DetraccionId implements Serializable {
    private Long facturaNumero;
    private String serienumero;
}
