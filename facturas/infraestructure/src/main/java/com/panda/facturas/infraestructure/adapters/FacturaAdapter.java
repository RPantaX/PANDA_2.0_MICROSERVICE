package com.panda.facturas.infraestructure.adapters;

import com.panda.facturas.domain.aggregates.constants.Constants;
import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.exceptions.FacturaAppExceptionBadRequest;
import com.panda.facturas.domain.aggregates.exceptions.FacturaAppExceptionNotFound;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.aggregates.request.RequestFacturaDetalle;
import com.panda.facturas.domain.aggregates.response.*;
import com.panda.facturas.domain.ports.out.FacturaServiceOut;
import com.panda.facturas.infraestructure.entity.EmisorEntity;
import com.panda.facturas.infraestructure.entity.FacturaDetalleEntity;
import com.panda.facturas.infraestructure.entity.FacturaEntity;
import com.panda.facturas.infraestructure.mapper.FacturaDetalleMapper;
import com.panda.facturas.infraestructure.mapper.FacturaMapper;
import com.panda.facturas.infraestructure.repository.EmisorRepository;
import com.panda.facturas.infraestructure.repository.FacturaDetalleRepository;
import com.panda.facturas.infraestructure.repository.FacturaRepository;
import com.panda.facturas.infraestructure.rest.client.ClienteSunat;
import com.panda.facturas.infraestructure.rest.msclient.ClientMSGuiaTranspt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturaAdapter implements FacturaServiceOut {
    private final FacturaRepository facturaRepository;
    private final FacturaDetalleRepository facturaDetalleRepository;
    private final FacturaMapper facturaMapper;
    private final FacturaDetalleMapper detalleMapper;
    private final ClienteSunat clienteSunat;
    private final ClientMSGuiaTranspt clientMSGuiaTranspt;
    private final EmisorRepository emisorRepository;
    @Value("${token.api}")
    private String tokenApi;

    @Override
    @Transactional
    public FacturaDTO crearFacturaOut(RequestFactura requestFactura) {
        ResponseSunat responseSunat = getSunatInfo(requestFactura.getClienteRuc());
        validarCliente(responseSunat);
        validarEmisor(requestFactura);
        BigDecimal subTotal = calcularSubtotal(requestFactura.getDetallesFacturas());
        BigDecimal total = calcularTotal(subTotal, requestFactura);

        validarGuiasTransp(requestFactura);

        FacturaEntity factura = construirFactura(requestFactura, subTotal, total);
        FacturaEntity facturaSaved = facturaRepository.save(factura);
        List<FacturaDetalleEntity> facturaDetalleEntityList = new ArrayList<>();
        for(RequestFacturaDetalle requestFacturaDetalle : requestFactura.getDetallesFacturas()) {
            FacturaDetalleEntity facturaDetalleEntity = FacturaDetalleEntity.builder()
                    .facturaNumero(requestFactura.getFacturaNumero())
                    .facturaSerie("E001")
                    .facturaSerienumero("E001" + requestFactura.getFacturaNumero())
                    .cantidad(requestFacturaDetalle.getCantidad())
                    .unidadMedida(requestFacturaDetalle.getUnidadMedida())
                    .descripcion(requestFacturaDetalle.getDescripcion())
                    .valorUnitario(requestFacturaDetalle.getValorUnitario())
                    .icbper(requestFacturaDetalle.getIcbper())
                    .build();
            facturaDetalleEntityList.add(facturaDetalleEntity);
        }

        facturaDetalleRepository.saveAll(facturaDetalleEntityList);
        return facturaMapper.mapFacturaToDto(facturaSaved);
    }
    private void validarEmisor(RequestFactura requestFactura) {
        Optional<EmisorEntity> emisorEntity = emisorRepository.findById(requestFactura.getClienteRuc());
        if(emisorEntity.isEmpty()) {
            throw new FacturaAppExceptionNotFound("Emisor no encontrado");
        }
    }

    @Override
    public Optional<ResponseGuiaTransptByFactura> buscarFacturaPorfacturaSerienumeroOut(String facturaSerienumero) {
        Optional<FacturaEntity> facturaEntityOptional = facturaRepository.findByFacturaSerienumero(facturaSerienumero);
        List<FacturaDetalleEntity> facturaDetalleEntity = facturaDetalleRepository.findAllByFacturaSerienumero(facturaSerienumero);
        if (facturaEntityOptional.isEmpty()) {
            throw new FacturaAppExceptionNotFound("El factura no existe");
        }
        if (facturaDetalleEntity.isEmpty()) {
            throw new FacturaAppExceptionBadRequest("No existen items en la factura, error Grave!");
        }
        List<ResponseGuiaTranspt> guiasTranspts;
        try {
            guiasTranspts = clientMSGuiaTranspt.ListarGuiasPorFacturaSerieNumero(facturaSerienumero);
            if (guiasTranspts == null) {
                throw new FacturaAppExceptionBadRequest("Error al buscar las guias con serieNumero: " + facturaSerienumero);
            }
        } catch (Exception e) {
            throw new FacturaAppExceptionBadRequest("Error al buscar las guias con serieNumero: " + facturaSerienumero);
        }
        ResponseGuiaTransptByFactura responseGuiaTransptByFactura = ResponseGuiaTransptByFactura.builder()
                .facturaDTO(facturaMapper.mapFacturaToDto(facturaEntityOptional.get()))
                .guiaTranspts(guiasTranspts)
                .facturaDetalleDTOList(facturaDetalleEntity.stream().map(detalleMapper::mapFacturaDetalleToDto).toList())
                .build();
        return Optional.of(responseGuiaTransptByFactura);
    }

    @Override
    public List<FacturaDTO> obtenerFacturasOut() {
        List<FacturaEntity> facturaEntities = facturaRepository.findAll();
        return facturaEntities.stream().map(facturaMapper::mapFacturaToDto).toList();
    }

    @Override
    public ResponseListPaginableFactura obtenerFacturasPaginableOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<FacturaEntity> facturaEntityPage = facturaRepository.findAll(pageable);

        List<FacturaEntity> facturaEntities = facturaEntityPage.getContent();
        List <FacturaDTO> facturaDTOList = facturaEntities.stream().map(facturaMapper::mapFacturaToDto).toList();

        return ResponseListPaginableFactura.builder()
                .facturaDetallesList(facturaDTOList)
                .numeroPagina(facturaEntityPage.getNumber())
                .medidaPagina(facturaEntityPage.getSize())
                .totalElementos(facturaEntityPage.getTotalElements())
                .ultima(facturaEntityPage.isLast())
                .build();
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
