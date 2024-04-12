package AlexSpring.GestioneDispositivi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(

        @NotEmpty(message = "Lo username è obbligatorio")
        @Size(min = 4, max = 25, message = "Lo username deve essere compreso tra 4 e 25 caratteri")
        String username,

        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 4, max = 25, message = "il deve essere compreso tra 4 e 25 caratteri")
        String name,


        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 4, max = 25, message = "il deve essere compreso tra 4 e 25 caratteri")
        String surname,


        @NotEmpty(message = "Email obbligatoria")
        @Email(message = "Formato email non valido")
        String email
) {

}
