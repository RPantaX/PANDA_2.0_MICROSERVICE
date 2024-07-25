package com.panda.msguiatransporte.controller;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msguiatransporte.aggregates.request.RequestGuiaTranspt;
import com.panda.msguiatransporte.ports.in.GuiaTransportistaIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/empresa")
@RequiredArgsConstructor
public class GuiaTransportistaController {
    private final GuiaTransportistaIn guiaTransportistaIn;
    @PostMapping
    public ResponseEntity<GuiaTransptDTO> registrarGuiaTransportista(@RequestBody RequestGuiaTranspt guiaTranspt) {
        GuiaTransptDTO guiaTransptDTO = guiaTransportistaIn.crearGuiaTransportistaIn(guiaTranspt);
        return ResponseEntity.ok(guiaTransptDTO);
    }
}
