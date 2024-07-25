package com.panda.msquiatransporte.infraestructure.adapters;

import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.request.RequestRemitente;
import com.panda.msguiatransporte.ports.out.RemitenteServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RemitenteAdapter implements RemitenteServiceOut {
    @Override
    public RemitenteDTO crearRemitenteOut(RequestDestinatario requestDestinatario) {
        return null;
    }

    @Override
    public Optional<RemitenteDTO> buscarRemitentePorRucOut(String ruc) {
        return Optional.empty();
    }

    @Override
    public List<RemitenteDTO> buscarRemitentesOut() {
        return List.of();
    }

    @Override
    public RemitenteDTO actualizarRemitenteOut(String ruc, RequestRemitente requestRemitente) {
        return null;
    }

    @Override
    public RemitenteDTO eliminarRemitenteOut(String ruc) {
        return null;
    }
}
