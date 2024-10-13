package com.panda.msguiatransporte.ports.in;

import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableDestinatario;

import java.util.List;
import java.util.Optional;

public interface DestinatarioServiceIn {
    DestinatarioDTO crearDestinatarioIn(RequestDestinatario requestDestinatario);
    Optional<DestinatarioDTO> buscarDestinatarioPorRucIn(String ruc);
    List<DestinatarioDTO> buscarDestinatariosIn();
    DestinatarioDTO actualizarDestinatarioIn(String ruc, RequestDestinatario requestDestinatario);
    DestinatarioDTO eliminarDestinatarioIn(String ruc);
    ResponseListPaginableDestinatario listarDestinatariosPaginableIn(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
}