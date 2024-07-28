package com.panda.msguiatransporte.controller;


import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import com.panda.msguiatransporte.aggregates.request.RequestRemitente;
import com.panda.msguiatransporte.impl.RemitenteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/v1/ms-guia-transportista/remitente")
@RequiredArgsConstructor
public class RemitenteController {
    private final RemitenteServiceImpl remitenteService;
    @PostMapping("/crear")
    public ResponseEntity<RemitenteDTO> registrarRemitente(@RequestBody RequestRemitente requestRemitente) {
        RemitenteDTO remitenteDTO = remitenteService.crearRemitenteIn(requestRemitente);
        return new ResponseEntity<>(remitenteDTO, HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<RemitenteDTO>> listarRemitente() {
        List<RemitenteDTO> remitenteDTOList = remitenteService.buscarRemitentesIn();
        return ResponseEntity.ok(remitenteDTOList);
    }
    @GetMapping("/listar/{ruc}")
    public ResponseEntity<RemitenteDTO>  listarRemitentePorRuc(@PathVariable("ruc") String ruc) {
        Optional<RemitenteDTO> remitenteDTO = remitenteService.buscarRemitentePorRucIn(ruc);
        return ResponseEntity.ok(remitenteDTO.get());
    }
    @PutMapping("/actualizar/{ruc}")
    public ResponseEntity<RemitenteDTO> actualizarRemitente(@PathVariable("ruc") String ruc, @RequestBody RequestRemitente requestRemitente) {
        RemitenteDTO remitenteDTO = remitenteService.actualizarRemitenteIn(ruc,requestRemitente);
        return ResponseEntity.ok(remitenteDTO);
    }
    @DeleteMapping("/eliminar/{ruc}")
    public ResponseEntity<RemitenteDTO> eliminarRemitente(@PathVariable("ruc") String ruc) {
        RemitenteDTO remitenteDTO = remitenteService.eliminarRemitenteIn(ruc);
        return new ResponseEntity<>(remitenteDTO, HttpStatus.NO_CONTENT);
    }
}
