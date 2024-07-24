package com.panda.msguiatransporte.ports.out;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msguiatransporte.aggregates.request.RequestGuiaTranspt;

import java.util.List;
import java.util.Optional;

public interface GuiaTransportistaOut {
    GuiaTransptDTO crearGuiaTransportistaOut(RequestGuiaTranspt requestGuiaTranspt);
    Optional<GuiaTransptDTO> obtenerGuiaTransportistaOut(String guiaTransptSerieNumero);
    List<GuiaTransptDTO> obtenerGuiaTransportistasOut();
}