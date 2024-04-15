package AlexSpring.GestioneDispositivi.services;

import AlexSpring.GestioneDispositivi.entities.Dipendente;
import AlexSpring.GestioneDispositivi.exceptions.UnauthorizedException;
import AlexSpring.GestioneDispositivi.payloads.DipendenteLoginDTO;
import AlexSpring.GestioneDispositivi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


        @Autowired
        private DipendenteService dipendenteService;

        @Autowired
        private JWTTools jwtTools;

    public String authenticateDipendente(DipendenteLoginDTO payload) {
    //* 1. Controllo le credenziali
        //*Cerco nel db tramite l'email l'utente
        Dipendente dipendente= this.dipendenteService.findByEmail(payload.email());

        //*Verifico se la password combacia con quella ricevuta dal payload
        if (dipendente.getUsername().equals(payload.username())){
                return jwtTools.createToken(dipendente);


        }else {
            throw  new UnauthorizedException("Credenziali non valide");
        }

    //* 2.Genero un token e lo ritorno

    //* 3. Se le credenziali non fossero corrette --> 401 (Unauthorized)
    }
}
