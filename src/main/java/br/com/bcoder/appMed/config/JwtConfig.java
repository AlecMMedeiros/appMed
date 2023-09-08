package br.com.bcoder.appMed.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class JwtConfig {
    @Bean
    public Key secretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}

