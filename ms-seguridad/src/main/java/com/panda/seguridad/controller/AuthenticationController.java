package com.panda.seguridad.controller;

import com.panda.seguridad.entity.Usuario;
import com.panda.seguridad.request.SignInRequest;
import com.panda.seguridad.request.SignUpRequest;
import com.panda.seguridad.response.AuthenticationResponse;
import com.panda.seguridad.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signupuser")
    public ResponseEntity<Usuario> signUpUser(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpUser(signUpRequest));
    }
    @PostMapping("/signupadmin")
    public ResponseEntity<Usuario> signUpAdmin(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpAdmin(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
}
