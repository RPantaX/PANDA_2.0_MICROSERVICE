package com.panda.facturas.application.controller;

import com.panda.facturas.domain.aggregates.constants.Constants;
import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableEmisor;
import com.panda.facturas.domain.ports.in.EmisorServiceIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ms-factura/emisor")
@RequiredArgsConstructor
public class EmisorController {
    private final EmisorServiceIn emisorServiceIn;
    @PostMapping("/crear")
    public ResponseEntity<?> registrarEmisor(@RequestBody @Valid RequestEmisor requestEmisor) {
        emisorServiceIn.crearEmisorIn(requestEmisor);
        return new ResponseEntity<>("Emisor Creado", HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<EmisorDTO>> listarEmisor() {
        List<EmisorDTO> emisorDTOList = emisorServiceIn.buscarEmisoresIn();
        return ResponseEntity.ok(emisorDTOList);
    }
    @GetMapping("/listar/paginable")
    public ResponseEntity<ResponseListPaginableEmisor> listarPaginableEmisor(@RequestParam(value = "pageNo", defaultValue = Constants.NUMERO_PAG_POR_DEFECTO, required = false) int numeroDePagina,
                                                                 @RequestParam(value = "pageSize", defaultValue = Constants.MEDIDA_PAG_POR_DEFECTO, required = false) int medidaDePagina,
                                                                 @RequestParam(value = "sortBy", defaultValue = Constants.ORDENAR_POR_DEFECTO_EMISOR, required = false) String ordenarPor,
                                                                 @RequestParam(value = "sortDir", defaultValue = Constants.ORDENAR_DIRECC_POR_DEFECTO, required = false) String sortDir) {
        ResponseListPaginableEmisor responseListPaginableEmisor= emisorServiceIn.listarPaginableEmisoresIn(
                numeroDePagina, medidaDePagina, ordenarPor, sortDir);
        return ResponseEntity.ok(responseListPaginableEmisor);
    }
    @GetMapping("/listar/{ruc}")
    public ResponseEntity<EmisorDTO>  listarEmisorPorRuc(@PathVariable("ruc") String ruc) {
        Optional<EmisorDTO> emisorDTO = emisorServiceIn.buscarEmisorPorRucIn(ruc);
        return ResponseEntity.ok(emisorDTO.get());
    }
    @PutMapping("/actualizar/{ruc}")
    public ResponseEntity<EmisorDTO> actualizarEmisor(@PathVariable("ruc") String ruc, @RequestBody RequestEmisor requestEmisor) {
        EmisorDTO emisorDTO = emisorServiceIn.actualizarEmisorIn(ruc,requestEmisor);
        return ResponseEntity.ok(emisorDTO);
    }
    @DeleteMapping("/eliminar/{ruc}")
    public ResponseEntity<EmisorDTO> eliminarEmisor(@PathVariable("ruc") String ruc) {
        EmisorDTO emisorDTO = emisorServiceIn.eliminarEmisorIn(ruc);
        return new ResponseEntity<>(emisorDTO, HttpStatus.NO_CONTENT);
    }
}
