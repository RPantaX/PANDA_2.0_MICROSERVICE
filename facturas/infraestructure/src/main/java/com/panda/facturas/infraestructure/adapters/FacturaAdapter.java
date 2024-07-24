package com.panda.facturas.infraestructure.adapters;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.domain.aggregates.request.RequestFactura;
import com.panda.facturas.domain.aggregates.response.ResponseSunat;
import com.panda.facturas.domain.ports.out.FacturaServiceOut;
import com.panda.facturas.infraestructure.mapper.FacturaMapper;
import com.panda.facturas.infraestructure.repository.FacturaDetalleRepository;
import com.panda.facturas.infraestructure.repository.FacturaRepository;
import com.panda.facturas.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturaAdapter implements FacturaServiceOut {
    private final FacturaRepository facturaRepository;
    private final FacturaDetalleRepository facturaDetalleRepository;
    private final FacturaMapper facturaMapper;
    private final ClienteSunat clienteSunat;
    @Value("${token.api}")
    private String tokenApi;

    @Override
    @Transactional
    public FacturaDTO crearFacturaOut(RequestFactura requestFactura) {
        // Obtener información del remitente desde Sunat
        ResponseSunat responseSunat  = getExecution(requestFactura.getClienteRuc());
        validarCliente(responseSunat);
        // Obtener la guía transportista
        //Optional<GuiaTransportista> guia = guiaTransportistaRepository.getGuiaTransportistaByNumeroGuia(request.getNumeroGuia());
        //validarGuiaTransportista(guia);
        return null;
    }

    @Override
    public Optional<FacturaDTO> buscarFacturaPorfacturaSerienumeroOut(String facturaSerienumero) {
        return Optional.empty();
    }

    @Override
    public List<FacturaDTO> obtenerFacturasOut() {
        return List.of();
    }
    public ResponseSunat getExecution(String numero){
        String authorization = "Bearer " + tokenApi;
        return clienteSunat.getInfoSunat(numero, authorization);
    }
    private void validarCliente(ResponseSunat responseCliente) {
        if (responseCliente == null) {
            throw new IllegalArgumentException("El RUC del cliente no esta disponible.");
        }
    }

}
