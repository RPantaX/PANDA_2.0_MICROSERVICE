package com.panda.msquiatransporte.infraestructure.adapters;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArchivoAdapter {
    private static final String UPLOAD_DIR = "archivos";
    public ArchivoAdapter() throws IOException{
        Files.createDirectories(Path.of(UPLOAD_DIR));
    }

    public String guardarArchivo(MultipartFile archivo) throws IOException {
        Path filePath = Path.of(UPLOAD_DIR, archivo.getOriginalFilename());
        Files.copy(archivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "El archivo fue guardado con exito: "+archivo.getOriginalFilename();
    }

    public Map<String, Object> obtenerNombresIniciaConJ(String nombreArchivo) throws IOException{
        Path filePath = Path.of(UPLOAD_DIR, nombreArchivo);
        Map<String, Object> response = new HashMap<>();

        if (!Files.exists(filePath)) {
            response.put("fecha", obtenerFechaActual());
            response.put("mensaje", "Archivo no encontrado: " + nombreArchivo);
            return response;
        }

        List<String> lineas = Files.readAllLines(filePath);

        if (lineas.isEmpty()) {
            response.put("fecha", obtenerFechaActual());
            response.put("mensaje", "No existen registros de nombres para ser procesados");
            return response;
        }


        List<String> nombres = lineas
                .stream()
                .filter(line -> !line.trim().isEmpty()) // Filtrar líneas vacías
                .flatMap(line -> Stream.of(line.split(","))) // Dividir por comas
                .map(String::trim) // Eliminar espacios
                .filter(nombre -> !nombre.isEmpty() && nombre.startsWith("J")) // Filtrar nombres que inicien con 'J'
                .map(String::toUpperCase) // Convertir a mayúsculas
                .collect(Collectors.toList());

        // Validar si hay nombres
        if (nombres.isEmpty()) {
            response.put("fecha", obtenerFechaActual());
            response.put("mensaje", "No existen registros on inicial con 'J' para ser procesados");
            return response;
        }

        response.put("fecha", obtenerFechaActual());
        response.put("mensaje", "Lista de nombres");
        response.put("nombres", nombres);
        return response;
    }
    public static String obtenerFechaActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
