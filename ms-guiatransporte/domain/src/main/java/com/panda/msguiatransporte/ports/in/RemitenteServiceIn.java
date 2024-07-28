package com.panda.msguiatransporte.ports.in;

import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestRemitente;

import java.util.List;
import java.util.Optional;

public interface RemitenteServiceIn {
    RemitenteDTO crearRemitenteIn(RequestRemitente requestRemitente);
    Optional<RemitenteDTO> buscarRemitentePorRucIn(String ruc);
    List<RemitenteDTO> buscarRemitentesIn();
    RemitenteDTO actualizarRemitenteIn(String ruc, RequestRemitente requestRemitente);
    RemitenteDTO eliminarRemitenteIn (String ruc);
}
