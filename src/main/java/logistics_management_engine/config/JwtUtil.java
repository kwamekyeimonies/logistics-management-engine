package logistics_management_engine.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import logistics_management_engine.models.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.refreshExpiration}")
    private Long refreshExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(UserDetails user_details, Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", employee.getRole());
        claims.put("staff_id", employee.getStaff_id());
        claims.put("id", employee.getId());
        claims.put("first_name", employee.getFirst_name());
        claims.put("last_name", employee.getLast_name());
        claims.put("email", employee.getEmail());
        claims.put("created_at", employee.getCreated_at().toString());
        claims.put("phone_number", employee.getPhone_number());
        return createToken(claims, user_details.getUsername(), expiration);

    }

    public String generateRefreshToken(UserDetails userDetails, Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", employee.getRole());
        claims.put("staff_id", employee.getStaff_id());
        claims.put("id", employee.getId());
        claims.put("first_name", employee.getFirst_name());
        claims.put("last_name", employee.getLast_name());
        claims.put("email", employee.getEmail());
        claims.put("created_at", employee.getCreated_at().toString());
        claims.put("phone_number", employee.getPhone_number());
        return createToken(claims, userDetails.getUsername(), refreshExpiration);
    }


    private String createToken(Map<String, Object> claims, String subject, Long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
