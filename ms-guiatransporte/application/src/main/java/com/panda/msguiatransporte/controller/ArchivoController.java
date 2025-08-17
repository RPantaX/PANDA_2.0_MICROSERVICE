package com.panda.msguiatransporte.controller;

import com.panda.msquiatransporte.infraestructure.adapters.ArchivoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/v1/ms-guia-transportista/archivo")
@RequiredArgsConstructor
public class ArchivoController {

    private final ArchivoAdapter archivoService;
    private static final String MENSAJE = "mensaje";

    @PostMapping("/archivos")
    public ResponseEntity<String> subirArchivo(@RequestParam("archivo") MultipartFile archivo){
        try {
            return ResponseEntity.ok(archivoService.guardarArchivo(archivo));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir el archivo: " + e.getMessage());
        }
    }
    @GetMapping("/archivos/obtener-nombres")
    public ResponseEntity<Map<String, Object>> obtenerNombresIniciaConJ(@RequestParam String nombreArchivo) {

        try {
            Map<String, Object> response = archivoService.obtenerNombresIniciaConJ(nombreArchivo);

            // Si no hay nombres en el archivo
            if (response.get(MENSAJE).equals("No existen registros de nombres para ser procesados")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            if (response.get(MENSAJE).equals("No existen registros on inicial con 'J' para ser procesados")) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            if(response.get(MENSAJE).equals("Archivo no encontrado: " + nombreArchivo)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, Object> errorResponse = Map.of(
                    "fecha", obtenerFechaActual(),
                    MENSAJE, "Error al procesar el archivo: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping("/archivos/descargar")
    public ResponseEntity<InputStreamResource> descargarArchivo(@RequestParam String nombreArchivo) {
        try {
            Path filePath = Path.of("archivos", nombreArchivo);
            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(Files.size(filePath))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    public static String obtenerFechaActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
