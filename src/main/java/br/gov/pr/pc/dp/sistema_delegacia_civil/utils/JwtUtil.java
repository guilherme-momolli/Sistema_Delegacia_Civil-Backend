package br.gov.pr.pc.dp.sistema_delegacia_civil.utils;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.Privilegio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationTime}")
    private long expirationTime;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, Long delegaciaId, String delegaciaNome, String usuarioNome, Privilegio privilegio) {
        return Jwts.builder()
                .setSubject(email)
                .claim("delegaciaId", delegaciaId)
                .claim("delegaciaNome", delegaciaNome)
                .claim("usuarioNome", usuarioNome)
                .claim("privilegio", privilegio)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (MalformedJwtException e) {
            System.out.println("Token inválido");
        } catch (SignatureException e) {
            System.out.println("Assinatura inválida");
        } catch (Exception e) {
            System.out.println("Erro ao validar token");
        }
        return false;
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractInstituicaoId(String token) {
        return extractClaim(token, claims -> claims.get("delegaciaId", Long.class));
    }

    public String extractInstituicaoNome(String token) {
        return extractClaim(token, claims -> claims.get("delegaciaNome", String.class));
    }

    public String extractUsuarioNome(String token) {
        return extractClaim(token, claims -> claims.get("usuarioNome", String.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public String extractPrivilegio(String token) {
        return extractClaim(token, claims -> claims.get("privilegio", String.class));
    }



}
