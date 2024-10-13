package com.panda.msguiatransporte.controller;

import com.panda.msguiatransporte.aggregates.constants.Constants;
import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableDestinatario;
import com.panda.msguiatransporte.ports.in.DestinatarioServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ms-guia-transportista/destinatario")
@RequiredArgsConstructor
public class DestinatarioController {
    private final DestinatarioServiceIn destinatarioService;

    @PostMapping("/crear")
    public ResponseEntity<DestinatarioDTO> registrarDestinatario(@RequestBody RequestDestinatario requestDestinatario) {
        DestinatarioDTO destinatarioDTO = destinatarioService.crearDestinatarioIn(requestDestinatario);
        return new ResponseEntity<>(destinatarioDTO, HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<DestinatarioDTO>> listarDestinatarios() {
        List<DestinatarioDTO> destinatarioDTOList = destinatarioService.buscarDestinatariosIn();
        return ResponseEntity.ok(destinatarioDTOList);
    }
    @GetMapping("/listar/{ruc}")
    public ResponseEntity<DestinatarioDTO>  listarDestinatarioPorRuc(@PathVariable("ruc") String ruc) {
        Optional<DestinatarioDTO> destinatarioDTO = destinatarioService.buscarDestinatarioPorRucIn(ruc);
        return ResponseEntity.ok(destinatarioDTO.get());
    }
    @PutMapping("/actualizar/{ruc}")
    public ResponseEntity<DestinatarioDTO> actualizarDestinatario(@PathVariable("ruc") String ruc, @RequestBody RequestDestinatario requestDestinatario) {
        DestinatarioDTO destinatarioDTO = destinatarioService.actualizarDestinatarioIn(ruc,requestDestinatario);
        return ResponseEntity.ok(destinatarioDTO);
    }
    @DeleteMapping("/eliminar/{ruc}")
    public ResponseEntity<DestinatarioDTO> eliminarDestinatario(@PathVariable("ruc") String ruc) {
        DestinatarioDTO destinatarioDTO = destinatarioService.eliminarDestinatarioIn(ruc);
        return new ResponseEntity<>(destinatarioDTO, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/listar/paginable")
    public ResponseEntity<ResponseListPaginableDestinatario> listarPaginableDestinatario(@RequestParam(value = "pageNo", defaultValue = Constants.NUMERO_PAG_POR_DEFECTO, required = false) int numeroDePagina,
                                                                                   @RequestParam(value = "pageSize", defaultValue = Constants.MEDIDA_PAG_POR_DEFECTO, required = false) int medidaDePagina,
                                                                                   @RequestParam(value = "sortBy", defaultValue = Constants.ORDENAR_POR_DEFECTO_DESTINATARIO, required = false) String ordenarPor,
                                                                                   @RequestParam(value = "sortDir", defaultValue = Constants.ORDENAR_DIRECC_POR_DEFECTO, required = false) String sortDir) {
        ResponseListPaginableDestinatario responseListPaginableDestinatario= destinatarioService.listarDestinatariosPaginableIn(
                numeroDePagina, medidaDePagina, ordenarPor, sortDir);
        return ResponseEntity.ok(responseListPaginableDestinatario);
    }
}
