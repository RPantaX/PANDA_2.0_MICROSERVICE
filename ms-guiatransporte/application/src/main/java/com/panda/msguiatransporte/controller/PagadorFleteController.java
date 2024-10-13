package com.panda.msguiatransporte.controller;

import com.panda.msguiatransporte.aggregates.constants.Constants;
import com.panda.msguiatransporte.aggregates.dto.PagadorFleteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestPagadorFlete;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginablePagador;
import com.panda.msguiatransporte.ports.in.PagadorFleteServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ms-guia-transportista/pagadorFlete")
@RequiredArgsConstructor
public class PagadorFleteController {
    private final PagadorFleteServiceIn pagadorFleteServiceIn;
    @PostMapping("/crear")
    public ResponseEntity<PagadorFleteDTO> registrarPagadorFlete(@RequestBody RequestPagadorFlete requestPagadorFlete) {
        PagadorFleteDTO pagadorFleteDTO = pagadorFleteServiceIn.crearPagadorFleteIn(requestPagadorFlete);
        return new ResponseEntity<>(pagadorFleteDTO, HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<PagadorFleteDTO>> listarPagadorFletes() {
        List<PagadorFleteDTO> pagadorFleteDTOList = pagadorFleteServiceIn.buscarPagadoresIn();
        return ResponseEntity.ok(pagadorFleteDTOList);
    }
    @GetMapping("/listar/{ruc}")
    public ResponseEntity<PagadorFleteDTO>  listarPagadorFletePorRuc(@PathVariable("ruc") String ruc) {
        Optional<PagadorFleteDTO> pagadorFleteDTO = pagadorFleteServiceIn.buscarPagadorPorRucIn(ruc);
        return ResponseEntity.ok(pagadorFleteDTO.get());
    }
    @PutMapping("/actualizar/{ruc}")
    public ResponseEntity<PagadorFleteDTO> actualizarPagadorFlete(@PathVariable("ruc") String ruc, @RequestBody RequestPagadorFlete requestPagadorFlete) {
        PagadorFleteDTO pagadorFleteDTO = pagadorFleteServiceIn.actualizarPagadorIn(ruc,requestPagadorFlete);
        return ResponseEntity.ok(pagadorFleteDTO);
    }
    @DeleteMapping("/eliminar/{ruc}")
    public ResponseEntity<PagadorFleteDTO> eliminarPagadorFlete(@PathVariable("ruc") String ruc) {
        PagadorFleteDTO pagadorFleteDTO = pagadorFleteServiceIn.eliminarPagadorIn(ruc);
        return new ResponseEntity<>(pagadorFleteDTO, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/listar/paginable")
    public ResponseEntity<ResponseListPaginablePagador> listarPaginablePagador(@RequestParam(value = "pageNo", defaultValue = Constants.NUMERO_PAG_POR_DEFECTO, required = false) int numeroDePagina,
                                                                              @RequestParam(value = "pageSize", defaultValue = Constants.MEDIDA_PAG_POR_DEFECTO, required = false) int medidaDePagina,
                                                                              @RequestParam(value = "sortBy", defaultValue = Constants.ORDENAR_POR_DEFECTO_PAGADOR_FLETE, required = false) String ordenarPor,
                                                                              @RequestParam(value = "sortDir", defaultValue = Constants.ORDENAR_DIRECC_POR_DEFECTO, required = false) String sortDir) {
        ResponseListPaginablePagador responseListPaginablePagador= pagadorFleteServiceIn.listaPaginablePagadoresIn(
                numeroDePagina, medidaDePagina, ordenarPor, sortDir);
        return ResponseEntity.ok(responseListPaginablePagador);
    }
}
