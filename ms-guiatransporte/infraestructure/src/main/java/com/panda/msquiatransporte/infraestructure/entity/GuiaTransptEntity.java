package com.panda.msquiatransporte.infraestructure.entity;

import com.panda.msquiatransporte.infraestructure.entity.compoundKeys.GuiaTransptId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Guia_transportista")
@IdClass(GuiaTransptId.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuiaTransptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guia_transp_numero", nullable = false)
    private Long guiaTranspNumero;

    @Id
    @Column(name = "guia_transp_serie", nullable = false, length = 15)
    private String guiaTranspSerie;

    @Column(name = "guia_transp_serienumero", nullable = false, length = 4)
    private String guiaTranspSerienumero;

    @Column(name = "fecha_emision", nullable = false)
    private Timestamp fechaEmision;

    @Column(name = "fecha_inicio_traslado", nullable = false)
    private Timestamp fechaInicioTraslado;

    @Column(name = "registro_MTC", nullable = false, length = 10)
    private String registroMTC;

    @Column(name = "partida", nullable = false, length = 255)
    private String partida;

    @Column(name = "llegada", nullable = false, length = 255)
    private String llegada;

    @Column(name = "unidad_medida_peso_bruto", length = 4)
    private String unidadMedidaPesoBruto;

    @Column(name = "peso_bruto_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal pesoBrutoTotal;

    @Column(name = "transbordo_programado", nullable = false)
    private Boolean transbordoProgramado;

    @Column(name = "retorno_vehiculo_vacio", nullable = false)
    private Boolean retornoVehiculoVacio;

    @Column(name = "transporte_subcontratado", nullable = false)
    private Boolean transporteSubcontratado;

    @Column(name = "usuario_emisor", nullable = false, length = 15)
    private String usuarioEmisor;

    @Column(name = "dest_ruc", nullable = false, length = 11)
    private String destRuc;

    @Column(name = "remitente_ruc", nullable = false, length = 11)
    private String remitenteRuc;

    @Column(name = "pag_flete_ruc", nullable = false, length = 11)
    private String pagFleteRuc;

    @Column(name = "factura_serienumero", nullable = true, length = 15)
    private String facturaSerienumero;

    @Column(name = "doc_identidad", length = 15)
    private String docIdentidad;

    @Column(name = "viaje_prog_id")
    private Integer viajeProgId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dest_ruc", insertable = false, updatable = false)
    private DestinatarioEntity destinatario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "remitente_ruc", insertable = false, updatable = false)
    private RemitenteEntity remitente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pag_flete_ruc", insertable = false, updatable = false)
    private PagadorFleteEntity pagadorFlete;
}
