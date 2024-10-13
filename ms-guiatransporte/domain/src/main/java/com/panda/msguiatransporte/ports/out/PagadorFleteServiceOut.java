package com.panda.msguiatransporte.ports.out;

import com.panda.msguiatransporte.aggregates.dto.PagadorFleteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestPagadorFlete;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginablePagador;

import java.util.List;
import java.util.Optional;

public interface PagadorFleteServiceOut {
    PagadorFleteDTO crearPagadorFleteOut(RequestPagadorFlete requestPagadorFlete);
    Optional<PagadorFleteDTO> buscarPagadorPorRucOut (String ruc);
    List<PagadorFleteDTO> buscarPagadoresOut();
    PagadorFleteDTO actualizarPagadorOut(String ruc, RequestPagadorFlete requestPagadorFlete);
    PagadorFleteDTO eliminarPagadorOut(String ruc);
    ResponseListPaginablePagador listaPaginablePagadoresOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

}
