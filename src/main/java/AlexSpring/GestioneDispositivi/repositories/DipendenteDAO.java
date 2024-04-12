package AlexSpring.GestioneDispositivi.repositories;

import AlexSpring.GestioneDispositivi.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente, Integer> {


    Optional<Dipendente> findByEmail(String email);
}
