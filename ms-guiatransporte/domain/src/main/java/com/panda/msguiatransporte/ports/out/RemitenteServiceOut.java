package com.panda.msguiatransporte.ports.out;

import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.request.RequestRemitente;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableRemitente;

import java.util.List;
import java.util.Optional;

public interface RemitenteServiceOut {
    RemitenteDTO crearRemitenteOut(RequestRemitente requestRemitente);
    Optional<RemitenteDTO> buscarRemitentePorRucOut(String ruc);
    List<RemitenteDTO> buscarRemitentesOut();
    RemitenteDTO actualizarRemitenteOut(String ruc, RequestRemitente requestRemitente);
    RemitenteDTO eliminarRemitenteOut (String ruc);
    ResponseListPaginableRemitente listarRemitentePaginableOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
}
