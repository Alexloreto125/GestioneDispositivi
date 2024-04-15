package AlexSpring.GestioneDispositivi.security;

import AlexSpring.GestioneDispositivi.entities.Dipendente;
import AlexSpring.GestioneDispositivi.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;
    public String createToken(Dipendente dipendente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis())) //? DATA DI EMSSIONE TOKEN (IAT ISSUED AT)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))  //? DATA DI SCADENZA DEL TOKEN (EXPIRATION DATE)
                .subject(String.valueOf(dipendente.getId())) //? SUBJECT, A CHI APPARTIENE IL TOKEN
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) //? FIRMO IL TOKEN con algoritmo HMAC passandogli il SEGRETO come attributo
                .compact(); //? COMPATTO TUTTO IN UNA STRINGA


    }

    public void verifyToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
            // Il metodo .parse(token) mi lancerà delle eccezioni in caso di token scaduto o token manipolato
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
            // Non importa quale eccezione verrà lanciata da .parse(), a me alla fine interessa che tutte come risultato abbiano 401
        }

    }
}
