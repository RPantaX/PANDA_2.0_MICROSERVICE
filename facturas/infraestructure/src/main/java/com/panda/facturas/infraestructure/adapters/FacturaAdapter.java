package com.panda.facturas.infraestructure.adapters;

import com.panda.facturas.domain.aggregates.constants.Constants;
import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.exceptions.FacturaAppExceptionBadRequest;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.aggregates.request.RequestFacturaDetalle;
import com.panda.facturas.domain.aggregates.response.ResponseError;
import com.panda.facturas.domain.aggregates.response.ResponseGuiaTranspt;
import com.panda.facturas.domain.aggregates.response.ResponseGuiaTransptByFactura;
import com.panda.facturas.domain.aggregates.response.ResponseSunat;
import com.panda.facturas.domain.ports.out.FacturaServiceOut;
import com.panda.facturas.infraestructure.entity.FacturaDetalleEntity;
import com.panda.facturas.infraestructure.entity.FacturaEntity;
import com.panda.facturas.infraestructure.mapper.FacturaMapper;
import com.panda.facturas.infraestructure.repository.FacturaDetalleRepository;
import com.panda.facturas.infraestructure.repository.FacturaRepository;
import com.panda.facturas.infraestructure.rest.client.ClienteSunat;
import com.panda.facturas.infraestructure.rest.msclient.ClientMSGuiaTranspt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaAdapter implements FacturaServiceOut {
    private final FacturaRepository facturaRepository;
    private final FacturaDetalleRepository facturaDetalleRepository;
    private final FacturaMapper facturaMapper;
    private final ClienteSunat clienteSunat;
    private final ClientMSGuiaTranspt clientMSGuiaTranspt;
    @Value("${token.api}")
    private String tokenApi;

    @Override
    @Transactional
    public FacturaDTO crearFacturaOut(RequestFactura requestFactura) {
        ResponseSunat responseSunat = getSunatInfo(requestFactura.getClienteRuc());
        validarCliente(responseSunat);


        BigDecimal subTotal = calcularSubtotal(requestFactura.getDetallesFacturas());
        BigDecimal total = calcularTotal(subTotal, requestFactura);

        validarGuiasTransp(requestFactura);

        FacturaEntity factura = construirFactura(requestFactura, subTotal, total);
        FacturaEntity facturaSaved = facturaRepository.save(factura);
        saveFacturaDetalles(requestFactura);
        return facturaMapper.mapFacturaToDto(facturaSaved);
    }

    @Override
    public Optional<ResponseGuiaTransptByFactura> buscarFacturaPorfacturaSerienumeroOut(String facturaSerienumero) {
        return Optional.empty();
    }

    @Override
    public List<ResponseGuiaTransptByFactura> obtenerFacturasOut() {
        return List.of();
    }
    private ResponseSunat getSunatInfo(String clienteRuc) {
        String authorization = "Bearer " + tokenApi;
        return clienteSunat.getInfoSunat(clienteRuc, authorization);
    }

    private void validarCliente(ResponseSunat responseCliente) {
        if (responseCliente == null) {
            throw new IllegalArgumentException("El RUC del cliente no esta disponible.");
        }
    }

    private List<FacturaDetalleEntity> saveFacturaDetalles(RequestFactura requestFactura) {
        List<FacturaDetalleEntity> facturaDetalles = requestFactura.getDetallesFacturas().stream()
                .map(detalle -> FacturaDetalleEntity.builder()
                        .facturaNumero(requestFactura.getFacturaNumero())
                        .facturaSerie("E001")
                        .facturaSerienumero("E001" + requestFactura.getFacturaNumero())
                        .cantidad(detalle.getCantidad())
                        .unidadMedida(detalle.getUnidadMedida())
                        .descripcion(detalle.getDescripcion())
                        .valorUnitario(detalle.getValorUnitario())
                        .icbper(detalle.getIcbper())
                        .build())
                .collect(Collectors.toList());
        facturaDetalleRepository.saveAll(facturaDetalles);
        return facturaDetalles;
    }

    private BigDecimal calcularSubtotal(List<RequestFacturaDetalle> itemsSave) {
        return itemsSave.stream()
                .map(item -> BigDecimal.valueOf(item.getCantidad()).multiply(item.getValorUnitario()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularTotal(BigDecimal subTotal, RequestFactura requestFactura) {
        return subTotal.add(subTotal.multiply(Constants.IGV))
                .add(Optional.ofNullable(requestFactura.getDescuentos()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(requestFactura.getAnticipios()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(requestFactura.getIsc()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(requestFactura.getIcbper()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(requestFactura.getOtrosCargos()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(requestFactura.getOtrosTributos()).orElse(BigDecimal.ZERO));
    }

    private void validarGuiasTransp(RequestFactura requestFactura) {
        for (String guiaTransp : requestFactura.getGuiaTranspSerieNumero()) {
            String ultimosCuatroDigitos = guiaTransp.substring(guiaTransp.length() - 4);
            String primerosNumeros = guiaTransp.substring(0, guiaTransp.length() - 4);
            System.out.println("ultimosCuatroDigitos"+ultimosCuatroDigitos);
            System.out.println("primerosNumeros"+primerosNumeros);
            try {
                ResponseGuiaTranspt responseGuiaTranspt = clientMSGuiaTranspt
                        .listarGuiaTransportistaPorGuiaYSerie(Integer.parseInt(primerosNumeros), ultimosCuatroDigitos);

                if (responseGuiaTranspt == null) {
                    System.out.println("Guía no encontrada: " + guiaTransp);
                } else {
                    clientMSGuiaTranspt.referenciarFactura(guiaTransp, "E001" + requestFactura.getFacturaNumero());
                }
            } catch (Exception e) {
                System.out.println("Error al buscar la guía: " + guiaTransp);
                throw new FacturaAppExceptionBadRequest("Error al buscar la guía con serieNumero: " + guiaTransp);
            }
        }
    }

    private FacturaEntity construirFactura(RequestFactura requestFactura, BigDecimal subTotal, BigDecimal total) {
        return FacturaEntity.builder()
                .facturaNumero(requestFactura.getFacturaNumero())
                .facturaSerie("E001")
                .facturaSerienumero("E001" + requestFactura.getFacturaNumero())
                .fechaEmision(Timestamp.from(Instant.now()))
                .subtotal(subTotal)
                .igv(subTotal.multiply(Constants.IGV))
                .descuentos(requestFactura.getDescuentos())
                .anticipios(requestFactura.getAnticipios())
                .valorVenta(subTotal)
                .isc(requestFactura.getIsc())
                .icbper(requestFactura.getIcbper())
                .otrosCargos(requestFactura.getOtrosCargos())
                .otrosTributos(requestFactura.getOtrosTributos())
                .montoRedondeo(BigDecimal.ZERO)
                .total(total)
                .observacion(requestFactura.getObservacion())
                .usuarioModificador("usuarioActual") // Define este valor en tu sistema
                .emisorRuc(requestFactura.getEmisorRuc())
                .build();
    }

}
