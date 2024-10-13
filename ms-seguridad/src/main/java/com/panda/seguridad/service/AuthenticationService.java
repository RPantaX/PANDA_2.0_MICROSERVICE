package com.panda.seguridad.service;

import com.panda.seguridad.entity.Usuario;
import com.panda.seguridad.request.SignInRequest;
import com.panda.seguridad.request.SignUpRequest;
import com.panda.seguridad.response.AuthenticationResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationService {
    Usuario signUpUser(SignUpRequest signUpRequest);
    Usuario signUpAdmin(SignUpRequest signUpRequest);
    AuthenticationResponse signin(SignInRequest signInRequest) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;
    //AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
