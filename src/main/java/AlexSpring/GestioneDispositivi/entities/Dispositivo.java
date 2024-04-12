package AlexSpring.GestioneDispositivi.entities;

import AlexSpring.GestioneDispositivi.enums.StatoDispositivo;
import AlexSpring.GestioneDispositivi.enums.TipoDispositivo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Dispositivi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipo;


    @Enumerated(EnumType.STRING)
    private StatoDispositivo stato;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

}
