package com.panda.msguiatransporte.controller;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msguiatransporte.aggregates.request.RequestGuiaTranspt;
import com.panda.msguiatransporte.ports.in.GuiaTransportistaIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ms-guia-transportista/guiaTransportista")
@RequiredArgsConstructor
public class GuiaTransportistaController {
    private final GuiaTransportistaIn guiaTransportistaIn;

    @PostMapping("/crear")
    public ResponseEntity<GuiaTransptDTO> registrarGuiaTransportista(@RequestBody RequestGuiaTranspt guiaTranspt) {
        GuiaTransptDTO guiaTransptDTO = guiaTransportistaIn.crearGuiaTransportistaIn(guiaTranspt);
        return new ResponseEntity<>(guiaTransptDTO, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<GuiaTransptDTO>> listarGuiaTransportista() {
        List<GuiaTransptDTO> guiaTransptDTOList = guiaTransportistaIn.obtenerGuiaTransportistasIn();
        return ResponseEntity.ok(guiaTransptDTOList);
    }

    @GetMapping("/listarPorGuiaYSerie")
    public ResponseEntity<GuiaTransptDTO> listarGuiaTransportista(
            @RequestParam(value = "guiaNumero", defaultValue = "1", required = true) long guiaNumero,
            @RequestParam(value = "guiaSerie", defaultValue = "EG03", required = true) String guiaSerie) {
        Optional<GuiaTransptDTO> guiaTransptDTOList = guiaTransportistaIn.obtenerGuiaTransportistaIn(guiaNumero, guiaSerie);
        return ResponseEntity.ok(guiaTransptDTOList.get());
    }
    @PutMapping("/referenciarFactura")
    public ResponseEntity<GuiaTransptDTO> referenciarFactura(
            @RequestParam(value = "guiaTransptSerieNumero", defaultValue = "1", required = true) long guiaTransptSerieNumero) {

    }
}
