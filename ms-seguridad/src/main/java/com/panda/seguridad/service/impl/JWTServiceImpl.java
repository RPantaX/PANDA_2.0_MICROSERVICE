package com.panda.seguridad.service.impl;

import com.panda.seguridad.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    @Value("classpath:jwtKeys/private_key.pem")
    private Resource privateKeyFile;

    //metodos de apoyo
    @Override
    public String generateToken(UserDetails userDetails) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //subject: usuarios
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 120000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    @Override
    public String extractUserName(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //utilizamos la firma para acceder a todo el token y extraemos todos los valores del payload (claims)
    private Claims extractAllClaims(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    //algoritmo hs256 hexadecimal que tenga 256 bytes. Esta clase nos ayudará a firmar el token
    //lo unico que hace el metodo es devolver una clave para poder firmar, aqui aun no se firma nada.
    //nos va a cambiar nuestra llave en un arreglo de bytes para luego poder firmar nuestros tokens
    private Key getSignKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = loadPrivateKey(privateKeyFile);
        //byte[] keyBytes = Decoders.BASE64.decode("o91N7lhvq4Kq6sDzPB/qMeFq3/q92dVw0tOOGJHnF4U=");
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public boolean validateToken(String token, UserDetails userDetails) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
    //Método que lee la llave privada
    private byte[] loadPrivateKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes= Files.readAllBytes(Paths.get(resource.getURI())); //obtenemos la ruta de la llave publica.
        String privateKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "") //para que lea solo la parte codificada
                .replaceAll("\\s", ""); // "\\s" elimina los espacios en blanco

        return Decoders.BASE64.decode(privateKeyPEM);//decodificamos lo que queda dentro del string, en base 64 porque nuestra privateKey esta en base 64
    }
}
