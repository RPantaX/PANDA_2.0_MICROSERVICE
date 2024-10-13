package com.panda.msguiatransporte.impl;

import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.request.RequestRemitente;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableRemitente;
import com.panda.msguiatransporte.ports.in.RemitenteServiceIn;
import com.panda.msguiatransporte.ports.out.RemitenteServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RemitenteServiceImpl implements RemitenteServiceIn {
    private final RemitenteServiceOut remitenteServiceOut;
    @Override
    public RemitenteDTO crearRemitenteIn(RequestRemitente requestRemitente) {
        return remitenteServiceOut.crearRemitenteOut(requestRemitente);
    }

    @Override
    public Optional<RemitenteDTO> buscarRemitentePorRucIn(String ruc) {
        return remitenteServiceOut.buscarRemitentePorRucOut(ruc);
    }

    @Override
    public List<RemitenteDTO> buscarRemitentesIn() {
        return remitenteServiceOut.buscarRemitentesOut();
    }

    @Override
    public RemitenteDTO actualizarRemitenteIn(String ruc, RequestRemitente requestRemitente) {
        return remitenteServiceOut.actualizarRemitenteOut(ruc, requestRemitente);
    }

    @Override
    public RemitenteDTO eliminarRemitenteIn(String ruc) {
        return remitenteServiceOut.eliminarRemitenteOut(ruc);
    }

    @Override
    public ResponseListPaginableRemitente listarRemitentePaginableIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        return remitenteServiceOut.listarRemitentePaginableOut(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }
}
