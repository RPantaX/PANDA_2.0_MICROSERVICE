package com.panda.facturas.infraestructure.adapters;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;
import com.panda.facturas.domain.ports.out.EmisorServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmisorAdapter implements EmisorServiceOut {
    @Override
    public EmisorDTO crearEmisorOut(RequestEmisor requestEmisor) {
        return null;
    }

    @Override
    public Optional<EmisorDTO> buscarEmisorPorRucOut(String ruc) {
        return Optional.empty();
    }

    @Override
    public List<EmisorDTO> buscarEmisoresPorRucOut(String ruc) {
        return List.of();
    }

    @Override
    public EmisorDTO actualizarEmisorOut(String ruc, RequestEmisor requestEmisor) {
        return null;
    }

    @Override
    public EmisorDTO eliminarEmisorOut(String ruc) {
        return null;
    }
}
