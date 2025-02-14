package com.panda.facturas.domain.ports.out;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableEmisor;

import java.util.List;
import java.util.Optional;

public interface EmisorServiceOut {
    void crearEmisorOut (RequestEmisor requestEmisor);
    Optional<EmisorDTO> buscarEmisorPorRucOut (String ruc);
    List<EmisorDTO> buscarEmisoresOut ();
    EmisorDTO actualizarEmisorOut (String ruc, RequestEmisor requestEmisor);
    EmisorDTO eliminarEmisorOut (String ruc);
    ResponseListPaginableEmisor listarPaginableEmisoresOut (int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
}
