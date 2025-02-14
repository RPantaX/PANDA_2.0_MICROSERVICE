package com.panda.seguridad.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/security-service/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/hola")
    public ResponseEntity<String> saludoAdmin(){
        return ResponseEntity.ok("Hola Usuario");
    }
}