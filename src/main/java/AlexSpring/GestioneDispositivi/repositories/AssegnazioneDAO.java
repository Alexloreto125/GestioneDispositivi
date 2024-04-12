package AlexSpring.GestioneDispositivi.repositories;

import AlexSpring.GestioneDispositivi.entities.Assegnazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssegnazioneDAO extends JpaRepository<Assegnazione, Long> {
}
