package com.panda.msguiatransporte.ports.in;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msguiatransporte.aggregates.request.RequestGuiaTranspt;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableGuiaTranspt;

import java.util.List;
import java.util.Optional;

public interface GuiaTransportistaIn {

    GuiaTransptDTO crearGuiaTransportistaIn(RequestGuiaTranspt requestGuiaTranspt);
    Optional<GuiaTransptDTO> obtenerGuiaTransportistaIn(Long guiaTranspNumero, String guiaTranspSerie);
    List<GuiaTransptDTO> obtenerGuiaTransportistasIn();
    GuiaTransptDTO referenciarFacturaAGuiaTransptIn(String guiaTransptSerieNumero, String facturaSerieNumero);
    List<GuiaTransptDTO> ListarGuiasPorFacturaSerieNumeroIn(String facturaSerieNumero);
    ResponseListPaginableGuiaTranspt listarGuiasPorFacturaSerieNumeroIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
}
