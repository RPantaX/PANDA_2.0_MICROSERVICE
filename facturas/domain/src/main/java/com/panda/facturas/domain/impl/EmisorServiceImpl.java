package com.panda.facturas.domain.impl;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableEmisor;
import com.panda.facturas.domain.ports.in.EmisorServiceIn;
import com.panda.facturas.domain.ports.out.EmisorServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmisorServiceImpl implements EmisorServiceIn {
    private final EmisorServiceOut emisorServiceOut;

    @Override
    public void crearEmisorIn(RequestEmisor requestEmisor) {
        emisorServiceOut.crearEmisorOut(requestEmisor);
    }

    @Override
    public Optional<EmisorDTO> buscarEmisorPorRucIn(String ruc) {
        return emisorServiceOut.buscarEmisorPorRucOut(ruc);
    }

    @Override
    public List<EmisorDTO> buscarEmisoresIn() {
        return emisorServiceOut.buscarEmisoresOut();
    }

    @Override
    public EmisorDTO actualizarEmisorIn(String ruc, RequestEmisor requestEmisor) {
        return emisorServiceOut.actualizarEmisorOut(ruc, requestEmisor);
    }

    @Override
    public EmisorDTO eliminarEmisorIn(String ruc) {
        return emisorServiceOut.eliminarEmisorOut(ruc);
    }

    @Override
    public ResponseListPaginableEmisor listarPaginableEmisoresIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        return emisorServiceOut.listarPaginableEmisoresOut(numeroDePagina,medidaDePagina,ordenarPor,sortDir);
    }
}
