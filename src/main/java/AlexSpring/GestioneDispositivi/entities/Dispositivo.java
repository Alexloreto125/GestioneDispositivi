package AlexSpring.GestioneDispositivi.entities;

import AlexSpring.GestioneDispositivi.enums.StatoDispositivo;
import AlexSpring.GestioneDispositivi.enums.TipoDispositivo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipo;


    @Enumerated(EnumType.STRING)
    private StatoDispositivo stato;

    @Column(name = "dipendente_id")
    private Integer dipendenteId;

//    @ManyToOne
//    @JoinColumn(insertable = false, updatable = false)
//    private Dipendente dipendente;




//    public Dispositivo(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo) {
//        this.tipo= tipoDispositivo;
//        this.stato=statoDispositivo;
//    }
    public Dispositivo(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo, int dipendenteId) {
        this.tipo= tipoDispositivo;
        this.stato=statoDispositivo;
        this.dipendenteId= dipendenteId;
    }

}
