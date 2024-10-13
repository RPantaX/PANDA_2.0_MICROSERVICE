package com.panda.msguiatransporte.ports.out;

import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableDestinatario;

import java.util.List;
import java.util.Optional;

public interface DestinatarioServiceOut {
    DestinatarioDTO crearDestinatarioOut(RequestDestinatario requestDestinatario);
    Optional<DestinatarioDTO> buscarDestinatarioPorRucOut(String ruc);
    List<DestinatarioDTO> buscarDestinatariosOut();
    DestinatarioDTO actualizarDestinatarioOut(String ruc, RequestDestinatario requestDestinatario);
    DestinatarioDTO eliminarDestinatarioOut(String ruc);
    ResponseListPaginableDestinatario listarDestinatariosPaginableOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

}
