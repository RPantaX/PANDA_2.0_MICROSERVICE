package com.panda.facturas.domain.ports.in;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;

import java.util.List;
import java.util.Optional;

public interface EmisorServiceIn {
    EmisorDTO crearEmisorIn (RequestEmisor requestEmisor);
    Optional<EmisorDTO> buscarEmisorPorRucIn (String ruc);
    List<EmisorDTO> buscarEmisoresPorRucIn (String ruc);
    EmisorDTO actualizarEmisorIn (String ruc, RequestEmisor requestEmisor);
    EmisorDTO eliminarEmisorIn (String ruc);
}
