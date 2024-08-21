package com.panda.facturas.application.controller;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;
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
    public ResponseEntity<EmisorDTO> registrarEmisor(@RequestBody @Valid RequestEmisor requestEmisor) {
        EmisorDTO emisorDTO = emisorServiceIn.crearEmisorIn(requestEmisor);
        return new ResponseEntity<>(emisorDTO, HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<EmisorDTO>> listarEmisor() {
        List<EmisorDTO> emisorDTOList = emisorServiceIn.buscarEmisoresIn();
        return ResponseEntity.ok(emisorDTOList);
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
