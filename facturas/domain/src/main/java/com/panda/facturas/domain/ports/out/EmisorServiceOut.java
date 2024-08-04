package com.panda.facturas.domain.ports.out;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;

import java.util.List;
import java.util.Optional;

public interface EmisorServiceOut {
    EmisorDTO crearEmisorOut (RequestEmisor requestEmisor);
    Optional<EmisorDTO> buscarEmisorPorRucOut (String ruc);
    List<EmisorDTO> buscarEmisoresOut ();
    EmisorDTO actualizarEmisorOut (String ruc, RequestEmisor requestEmisor);
    EmisorDTO eliminarEmisorOut (String ruc);
}
