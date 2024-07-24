package com.panda.msguiatransporte.ports.in;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msguiatransporte.aggregates.request.RequestGuiaTranspt;

import java.util.List;
import java.util.Optional;

public interface GuiaTransportistaIn {

    GuiaTransptDTO crearGuiaTransportistaIn(RequestGuiaTranspt requestGuiaTranspt);
    Optional<GuiaTransptDTO> obtenerGuiaTransportistaIn(String guiaTransptSerieNumero);
    List<GuiaTransptDTO> obtenerGuiaTransportistasIn();

}
