package be.technifutur.user.service;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import be.technifutur.user.config.JwtProperties;
import be.technifutur.sharedclass.forms.LoginForm;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authManager;

    private final JwtProperties properties;

    public LoginService(JwtProperties properties) {
        this.properties = properties;
    }

    public String login(LoginForm form){
        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());

        authentication = authManager.authenticate(authentication);

        return properties.getPrefix() + JWT.create()
            .withSubject(form.getUsername())
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + properties.getExpires()))
            .withClaim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
            .sign(Algorithm.HMAC512(properties.getSecret()));        
    }
}
