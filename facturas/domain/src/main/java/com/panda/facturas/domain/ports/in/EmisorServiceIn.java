package com.panda.facturas.domain.ports.in;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableEmisor;

import java.util.List;
import java.util.Optional;

public interface EmisorServiceIn {
    void crearEmisorIn (RequestEmisor requestEmisor);
    Optional<EmisorDTO> buscarEmisorPorRucIn (String ruc);
    List<EmisorDTO> buscarEmisoresIn ();
    EmisorDTO actualizarEmisorIn (String ruc, RequestEmisor requestEmisor);
    EmisorDTO eliminarEmisorIn (String ruc);
    ResponseListPaginableEmisor listarPaginableEmisoresIn (int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
}
