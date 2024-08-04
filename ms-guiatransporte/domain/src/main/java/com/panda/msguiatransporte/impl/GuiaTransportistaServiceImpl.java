package com.panda.msguiatransporte.impl;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msguiatransporte.aggregates.request.RequestGuiaTranspt;
import com.panda.msguiatransporte.ports.in.GuiaTransportistaIn;
import com.panda.msguiatransporte.ports.out.GuiaTransportistaOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuiaTransportistaServiceImpl implements GuiaTransportistaIn {
    private final GuiaTransportistaOut guiaTransportistaOut;

    @Override
    public GuiaTransptDTO crearGuiaTransportistaIn(RequestGuiaTranspt requestGuiaTranspt) {
        return guiaTransportistaOut.crearGuiaTransportistaOut(requestGuiaTranspt);
    }

    @Override
    public Optional<GuiaTransptDTO> obtenerGuiaTransportistaIn(Long guiaTranspNumero, String guiaTranspSerie) {
        return guiaTransportistaOut.obtenerGuiaTransportistaOut(guiaTranspNumero,guiaTranspSerie);
    }

    @Override
    public List<GuiaTransptDTO> obtenerGuiaTransportistasIn() {
        return guiaTransportistaOut.obtenerGuiaTransportistasOut();
    }

    @Override
    public GuiaTransptDTO referenciarFacturaAGuiaTransptIn(String guiaTransptSerieNumero, String facturaSerieNumero) {
        return guiaTransportistaOut.referenciarFacturaAGuiaTransptOut(guiaTransptSerieNumero,facturaSerieNumero);
    }

    @Override
    public List<GuiaTransptDTO> ListarGuiasPorFacturaSerieNumeroIn(String facturaSerieNumero) {
        return guiaTransportistaOut.ListarGuiasPorFacturaSerieNumeroOut(facturaSerieNumero);
    }
}
