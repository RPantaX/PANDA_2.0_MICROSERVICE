package com.panda.facturas.infraestructure.entity;

import com.panda.facturas.infraestructure.entity.CompoundKeys.FacturaDetalleId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "factura")
@IdClass(FacturaDetalleId.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaEntity {
    @Id
    @Column(name = "factura_numero", nullable = false)
    private Long facturaNumero;
    @Id
    @Column(name = "factura_serie", nullable = false, length = 4)
    private String facturaSerie;

    @Column(name = "factura_serienumero", nullable = false, length = 15)
    private String facturaSerienumero;

    @Column(name = "fecha_emision", nullable = false)
    private Timestamp fechaEmision;

    @Column(name = "subtotal", nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "igv", nullable = false, precision = 15, scale = 2)
    private BigDecimal igv;

    @Column(name = "descuentos", nullable = true, precision = 15, scale = 2)
    private BigDecimal descuentos;

    @Column(name = "anticipios", nullable = true, precision = 15, scale = 2)
    private BigDecimal anticipios;

    @Column(name = "valor_venta", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorVenta;

    @Column(name = "isc", nullable = true, precision = 15, scale = 2)
    private BigDecimal isc;

    @Column(name = "icbper", nullable = true, precision = 15, scale = 2)
    private BigDecimal icbper;

    @Column(name = "otros_cargos", nullable = true, precision = 15, scale = 2)
    private BigDecimal otrosCargos;

    @Column(name = "otros_tributos", nullable = true, precision = 15, scale = 2)
    private BigDecimal otrosTributos;

    @Column(name = "monto_redondeo", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoRedondeo;

    @Column(name = "total", nullable = false, precision = 15, scale = 2)
    private BigDecimal total;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "usuario_modificador", nullable = false, length = 15)
    private String usuarioModificador;

    @Column(name = "emisor_ruc", nullable = false, length = 11)
    private String emisorRuc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "emisor_ruc", insertable = false, updatable = false)
    private EmisorEntity emisor;
}
