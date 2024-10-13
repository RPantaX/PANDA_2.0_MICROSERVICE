package com.panda.msguiatransporte.impl;

import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableDestinatario;
import com.panda.msguiatransporte.ports.in.DestinatarioServiceIn;
import com.panda.msguiatransporte.ports.out.DestinatarioServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class DestiatrioServiceImpl implements DestinatarioServiceIn {
    private final DestinatarioServiceOut destinatarioServiceOut;
    @Override
    public DestinatarioDTO crearDestinatarioIn(RequestDestinatario requestDestinatario) {
        return destinatarioServiceOut.crearDestinatarioOut(requestDestinatario);
    }

    @Override
    public Optional<DestinatarioDTO> buscarDestinatarioPorRucIn(String ruc) {
        return destinatarioServiceOut.buscarDestinatarioPorRucOut(ruc);
    }

    @Override
    public List<DestinatarioDTO> buscarDestinatariosIn() {
        return destinatarioServiceOut.buscarDestinatariosOut();
    }

    @Override
    public DestinatarioDTO actualizarDestinatarioIn(String ruc, RequestDestinatario requestDestinatario) {
        return destinatarioServiceOut.actualizarDestinatarioOut(ruc, requestDestinatario);
    }

    @Override
    public DestinatarioDTO eliminarDestinatarioIn(String ruc) {
        return destinatarioServiceOut.eliminarDestinatarioOut(ruc);
    }

    @Override
    public ResponseListPaginableDestinatario listarDestinatariosPaginableIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
       return destinatarioServiceOut.listarDestinatariosPaginableOut(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }
}
