//package br.gov.pr.pc.dp.sistema_delegacia_civil.utils;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.usuario.Privilegio;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//@Slf4j
//public class JWTUtil {
//
//    @Value("${jwt.secret:zp9jWqySkKqPqpg0ksqATgiGcYEqsgg8mDnCcZe2rSLmHJwz3vg2atskI+PODFqhSYyX/ByFbyTiOfITgriMYQ==}")
//    private String secretString;
//
//    @Value("${jwt.expirationTime:86400000}")
//    private long expirationMs;
//
//    @Value("${jwt.issuer:sistema-delegacia-civil}")
//    private String issuer;
//
//    private SecretKey key;
//
//    @PostConstruct
//    public void init() {
//        if (secretString == null || secretString.isBlank()) {
//            log.warn("JWT_SECRET não configurado! Usando chave gerada em memória (NÃO USE EM PRODUÇÃO)");
//            this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        } else {
//            if (secretString.length() < 64) {
//                log.warn("JWT_SECRET muito curta! Recomendado: 64+ caracteres (512+ bits)");
//            }
//            this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
//        }
//    }
//
//    public String generateToken(String subject, Map<String, Object> claims) {
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + expirationMs);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuer(issuer)
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    public String generateToken(String email, Long delegaciaId, String delegaciaNome, String nome, Privilegio privilegio) {
//        return generateToken(email, Map.of(
//                "delegaciaId", delegaciaId,
//                "delegaciaNome", delegaciaNome,
//                "nome", nome,
//                "privilegio", privilegio.name(),
//                "scope", "USER"
//        ));
//    }
//
//    public boolean isValid(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .requireIssuer(issuer)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            log.debug("Token JWT inválido: {} - {}", e.getClass().getSimpleName(), e.getMessage());
//            return false;
//        }
//    }
//
//    public boolean isExpired(String token) {
//        try {
//            return extractExpiration(token).before(new Date());
//        } catch (JwtException | IllegalArgumentException e) {
//            return true;
//        }
//    }
//
//    public String extractSubject(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, String claimName, Class<T> type) {
//        return extractClaim(token, claims -> claims.get(claimName, type));
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
//        try {
//            Claims claims = extractAllClaims(token);
//            return resolver.apply(claims);
//        } catch (JwtException | IllegalArgumentException e) {
//            log.debug("Falha ao extrair claim: {}", e.getMessage());
//            return null;
//        }
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public String extractEmail(String token) {
//        return extractSubject(token);
//    }
//
//    public Long extractDelegaciaId(String token) {
//        return extractClaim(token, "delegaciaId", Long.class);
//    }
//
//    public String extractDelegaciaNome(String token) {
//        return extractClaim(token, "delegaciaNome", String.class);
//    }
//
//    public String extractNome(String token) {
//        return extractClaim(token, "nome", String.class);
//    }
//
//    public Privilegio extractPrivilegio(String token) {
//        String priv = extractClaim(token, "privilegio", String.class);
//        try {
//            return priv != null ? Privilegio.valueOf(priv) : null;
//        } catch (IllegalArgumentException e) {
//            log.warn("Privilegio inválido no token: {}", priv);
//            return null;
//        }
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public String extractIssuer(String token) {
//        return extractClaim(token, Claims::getIssuer);
//    }
//
//    public long getExpirationMs() {
//        return expirationMs;
//    }
//}
