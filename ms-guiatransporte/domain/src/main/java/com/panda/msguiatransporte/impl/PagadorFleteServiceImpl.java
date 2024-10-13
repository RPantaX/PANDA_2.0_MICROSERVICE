package com.panda.msguiatransporte.impl;

import com.panda.msguiatransporte.aggregates.dto.PagadorFleteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestPagadorFlete;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginablePagador;
import com.panda.msguiatransporte.ports.in.PagadorFleteServiceIn;
import com.panda.msguiatransporte.ports.out.PagadorFleteServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagadorFleteServiceImpl implements PagadorFleteServiceIn {
    private final PagadorFleteServiceOut pagadorFleteServiceOut;
    @Override
    public PagadorFleteDTO crearPagadorFleteIn(RequestPagadorFlete requestPagadorFlete) {
        return pagadorFleteServiceOut.crearPagadorFleteOut(requestPagadorFlete);
    }

    @Override
    public Optional<PagadorFleteDTO> buscarPagadorPorRucIn(String ruc) {
        return pagadorFleteServiceOut.buscarPagadorPorRucOut(ruc);
    }

    @Override
    public List<PagadorFleteDTO> buscarPagadoresIn() {
        return pagadorFleteServiceOut.buscarPagadoresOut();
    }

    @Override
    public PagadorFleteDTO actualizarPagadorIn(String ruc, RequestPagadorFlete requestPagadorFlete) {
        return pagadorFleteServiceOut.actualizarPagadorOut(ruc, requestPagadorFlete);
    }

    @Override
    public PagadorFleteDTO eliminarPagadorIn(String ruc) {
        return pagadorFleteServiceOut.eliminarPagadorOut(ruc);
    }

    @Override
    public ResponseListPaginablePagador listaPaginablePagadoresIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        return pagadorFleteServiceOut.listaPaginablePagadoresOut(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }
}
