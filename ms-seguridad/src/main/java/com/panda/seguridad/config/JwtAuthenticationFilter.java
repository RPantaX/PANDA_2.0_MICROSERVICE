package com.panda.seguridad.config;

import ch.qos.logback.core.util.StringUtil;
import com.panda.seguridad.service.JWTService;
import com.panda.seguridad.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (StringUtil.isNullOrEmpty(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            //si no encuentra algun autHeader o si la clave no empieza con "Bearer " que pase el filtro para luego verificar si realmente está accediendo a un enpoint libre
            filterChain.doFilter(request, response);
            return;
        }
        //para que no considere los 7 espacios del "Bearer "
        jwt = authHeader.substring(7);
        try {
            username = jwtService.extractUserName(jwt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        //validamos que el username no sea nulo y que este usuario no esté authenticado.
        if (Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = usuarioService.userDetailsService().loadUserByUsername(username);
            try {
                if (jwtService.validateToken(jwt, userDetails)) {
                    //la persona si existe y el token si es válido, entonces has lo siquiente...
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    //a la autenticacion le ponemos el userDetails y los roles
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    //seteamos el contexto, porque habiamos creado un contexto vacío
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(authentication);
                    //al final le pasamos el contexto al SecurityContextHolder para que este lo maneje ya que aqui estarán todas las authenticaciones
                    SecurityContextHolder.setContext(securityContext);
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }
        //si ya esta authenticado quiere decir que es el mismo usuario el que está haciendo una petición
        filterChain.doFilter(request, response);
    }
}
