package AlexSpring.GestioneDispositivi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Dipendenti")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dipendente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private String username;
    private String name;
    private String surname;
    private String email;


    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositiviAssegnati;

    public Dipendente(String username, String name, String surname, String email) {
        this.username= username;
        this.name= name;
        this.surname= surname;
        this.email= email;
    }
}