package com.panda.facturas.infraestructure.entity;

import com.panda.facturas.infraestructure.entity.CompoundKeys.DetraccionId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "detraccion")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetraccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "factura_numero", nullable = false)
    private Long facturaNumero;

    @Column(name = "factura_serie", nullable = false, length = 4)
    private String facturaSerie;

    @Column(name = "factura_serienumero", nullable = false, length = 15)
    private String serienumero;
    @Column(name = "leyenda", nullable = false, length = 255)
    private String leyenda;

    @Column(name = "servicio", nullable = false, length = 255)
    private String servicio;

    @Column(name = "medio_pago", nullable = false, length = 255)
    private String medioPago;

    @Column(name = "nro_cuenta", nullable = false, length = 11)
    private String nroCuenta;

    @Column(name = "detraccion_porcentaje", nullable = false)
    private Integer detraccionPorcentaje;

    @Column(name = "detraccion_monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal detraccionMonto;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "usuario_modificador", nullable = false, length = 15)
    private String usuarioModificador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "factura_numero", referencedColumnName = "factura_numero", insertable = false, updatable = false),
            @JoinColumn(name = "factura_serie", referencedColumnName = "factura_serie", insertable = false, updatable = false)
    })
    private FacturaEntity factura;
}
