package br.ifba.edu.agenda.services;

import br.ifba.edu.agenda.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JWTokenService {
    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("br.ifba.edu.agenda")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant dataExpiracao() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
            .plusHours(2)
            .toInstant();
    }

    // public String getSubject(String tokenJWT){
    //     try {
    //         var algoritmo = Algorithm.HMAC256(secret);
    //         return JWT.require(algoritmo)
    //                 .withIssuer("auth-api")
    //                 .build()
    //                 .verify(tokenJWT)
    //                 .getSubject();
    // } catch (JWTVerificationException exception){
    //     throw new RuntimeException("Token JWT inválido ou expirado!");
    //     }
    // }

    public String getSubject(String tokenJWT) {
    try {
        // Substitua pelo algoritmo e secret reais do seu projeto
        var algoritmo = Algorithm.HMAC256(secret); 
        
        return JWT.require(algoritmo)
                .withIssuer("br.ifba.edu.agenda") // Garanta que o Issuer bate exatamente com o do gerarToken
                .build()
                .verify(tokenJWT)
                .getSubject();
                
    } catch (JWTVerificationException exception) {
        // EM VEZ DE JOGAR RUNTIMEEXCEPTION: Retorna null!
        // Isso avisa o seu SecurityFilter que o token atual é inválido,
        // mas permite que rotas públicas como o cadastro continuem funcionando.
        return null; 
    }
}
}