package AlexSpring.GestioneDispositivi.repositories;

import AlexSpring.GestioneDispositivi.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoDAO extends JpaRepository<Dispositivo, Integer> {
}
