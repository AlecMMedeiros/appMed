package br.com.bcoder.appMed.utils;

import br.com.bcoder.appMed.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final Key key;

    @Autowired
    public JwtUtil(Key key ) {
        this.key = key;
    }

    @Autowired
    private UserRepository userRepository;

    public String generateJwtToken(String user) {
        long currentTimeMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 3600000))  // 1 hora de validade
                .signWith(key)
                .compact();
    }

    public String extractEmailFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, String user) {
        String userToVerify = userRepository.findByEmail(user).getEmail();
        final String username = extractEmailFromToken(token);
        return (username.equals(userToVerify) && !isTokenExpired(token));
    }


}
