package com.panda.msquiatransporte.infraestructure.adapters;

import com.panda.msguiatransporte.aggregates.dto.PagadorFleteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestPagadorFlete;
import com.panda.msguiatransporte.ports.out.PagadorFleteServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagadorAdapter implements PagadorFleteServiceOut {
    @Override
    public PagadorFleteDTO crearPagadorFleteOut(RequestPagadorFlete requestPagadorFlete) {
        return null;
    }

    @Override
    public Optional<PagadorFleteDTO> buscarPagadorPorRucOut(String ruc) {
        return Optional.empty();
    }

    @Override
    public List<PagadorFleteDTO> buscarPagadoresOut() {
        return List.of();
    }

    @Override
    public PagadorFleteDTO actualizarPagadorOut(String ruc, RequestPagadorFlete requestPagadorFlete) {
        return null;
    }

    @Override
    public PagadorFleteDTO eliminarPagadorOut(String ruc) {
        return null;
    }
}
