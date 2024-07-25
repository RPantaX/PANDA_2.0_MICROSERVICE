package com.panda.msquiatransporte.infraestructure.adapters;

import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseReniec;
import com.panda.msguiatransporte.ports.out.DestinatarioServiceOut;
import com.panda.msquiatransporte.infraestructure.repository.DestinatarioRepository;
import com.panda.msquiatransporte.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinatarioApdapter implements DestinatarioServiceOut {

    private final DestinatarioRepository destinatarioRepository;

    private final ClientReniec reniec;

    @Value("${token.api}")
    String tokenApi;

    @Override
    public DestinatarioDTO crearDestinatarioOut(RequestDestinatario requestDestinatario) {
        return null;
    }

    @Override
    public Optional<DestinatarioDTO> buscarDestinatarioPorRucOut(String ruc) {
        return Optional.empty();
    }

    @Override
    public List<DestinatarioDTO> buscarDestinatariosOut() {
        return List.of();
    }

    @Override
    public DestinatarioDTO actualizarDestinatarioOut(String ruc, RequestDestinatario requestDestinatario) {
        return null;
    }

    @Override
    public DestinatarioDTO eliminarDestinatarioOut(String ruc) {
        return null;
    }
    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero,authorization);
        return  responseReniec;
    }
}
