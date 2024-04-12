package AlexSpring.GestioneDispositivi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Assegnazioni")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assegnazione {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;


}
