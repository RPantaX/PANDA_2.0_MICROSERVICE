package com.panda.msguiatransporte.ports.in;

import com.panda.msguiatransporte.aggregates.dto.PagadorFleteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestPagadorFlete;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginablePagador;
import feign.Response;

import java.util.List;
import java.util.Optional;

public interface PagadorFleteServiceIn {
    PagadorFleteDTO crearPagadorFleteIn(RequestPagadorFlete requestPagadorFlete);
    Optional<PagadorFleteDTO> buscarPagadorPorRucIn (String ruc);
    List<PagadorFleteDTO> buscarPagadoresIn();
    PagadorFleteDTO actualizarPagadorIn(String ruc, RequestPagadorFlete requestPagadorFlete);
    PagadorFleteDTO eliminarPagadorIn(String ruc);
    ResponseListPaginablePagador listaPaginablePagadoresIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
}
