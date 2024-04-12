package AlexSpring.GestioneDispositivi.payloads;

import AlexSpring.GestioneDispositivi.enums.StatoDispositivo;
import AlexSpring.GestioneDispositivi.enums.TipoDispositivo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record NewDispositivoDTO (



        @NotNull(message = "Il tipo di dispositivo non può essere nullo")
        TipoDispositivo TipoDispositivo,

        @NotNull(message = "Lo stato del dispositivo non può essere nullo")

        StatoDispositivo StatoDispositivo,

        Integer dipendenteId

){

}
