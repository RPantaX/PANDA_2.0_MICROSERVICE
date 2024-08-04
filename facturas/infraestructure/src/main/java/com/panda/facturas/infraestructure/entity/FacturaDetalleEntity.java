package com.panda.facturas.infraestructure.entity;

import com.panda.facturas.infraestructure.entity.CompoundKeys.FacturaDetalleId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "factura_detalle")
@IdClass(FacturaEntity.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDetalleEntity {
    @Id
    @Column(name = "factura_numero", nullable = false)
    private Long facturaNumero;
    @Id
    @Column(name = "factura_serie", nullable = false, length = 4)
    private String facturaSerie;

    @Column(name = "factura_serienumero", nullable = false, length = 15)
    private String facturaSerienumero;


    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "unidad_medida", nullable = false, length = 4)
    private String unidadMedida;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "valor_unitario", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "icbper", nullable = true, precision = 15, scale = 2)
    private BigDecimal icbper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "factura_numero", referencedColumnName = "factura_numero", insertable = false, updatable = false),
            @JoinColumn(name = "factura_serie", referencedColumnName = "factura_serie", insertable = false, updatable = false)
    })
    private FacturaEntity factura;
}
