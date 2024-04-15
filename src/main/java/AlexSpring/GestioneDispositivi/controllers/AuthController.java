package AlexSpring.GestioneDispositivi.controllers;


import AlexSpring.GestioneDispositivi.payloads.DipendenteLoginDTO;
import AlexSpring.GestioneDispositivi.payloads.DipendenteLoginResponseDTO;
import AlexSpring.GestioneDispositivi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private AuthService authService;

    @PostMapping("/login")
    public DipendenteLoginResponseDTO login(@RequestBody DipendenteLoginDTO payload){
        return new DipendenteLoginResponseDTO(this.authService.authenticateDipendente(payload));
    }
}
