package AlexSpring.GestioneDispositivi.services;

import AlexSpring.GestioneDispositivi.entities.Assegnazione;
import AlexSpring.GestioneDispositivi.entities.Dipendente;
import AlexSpring.GestioneDispositivi.entities.Dispositivo;
import AlexSpring.GestioneDispositivi.enums.StatoDispositivo;
import AlexSpring.GestioneDispositivi.exceptions.BadRequestException;
import AlexSpring.GestioneDispositivi.exceptions.NotFoundException;
import AlexSpring.GestioneDispositivi.payloads.NewDispositivoDTO;
import AlexSpring.GestioneDispositivi.repositories.AssegnazioneDAO;
import AlexSpring.GestioneDispositivi.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoDAO dispositivoDAO;
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private AssegnazioneDAO assegnazioneDAO;


    //* RITORNO TUTTI I DISPOSITIVI CON PAGINAZIONE
    public Page<Dispositivo> getAllDispositivi(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return this.dispositivoDAO.findAll(pageable);

    }

    //*RITORNO IN BASE ID
    public Dispositivo findById(int id) {
        return this.dispositivoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    //* SALVO I DISPOSITIVI

    public Dispositivo create(NewDispositivoDTO newDispositivoDTO) {

        try {
            System.out.println(StatoDispositivo.valueOf(String.valueOf(newDispositivoDTO.StatoDispositivo())));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Stato dispositivo non valido: ");
        }


        Dispositivo dispositivo = new Dispositivo(newDispositivoDTO.TipoDispositivo(), newDispositivoDTO.StatoDispositivo(), newDispositivoDTO.dipendenteId());


        if (newDispositivoDTO.StatoDispositivo() == StatoDispositivo.ASSEGNATO && newDispositivoDTO.dipendenteId() != null) {
            if (newDispositivoDTO.dipendenteId() != null) {
                Dipendente dipendente = dipendenteService.findById(newDispositivoDTO.dipendenteId());

                if (dipendente != null) {
                    dispositivo = this.dispositivoDAO.save(dispositivo);
                    Assegnazione assegnazione = new Assegnazione();

                    assegnazione.setDipendente(dipendente);
                    assegnazione.setDispositivo(dispositivo);
                    this.assegnazioneDAO.save(assegnazione);
                    dispositivo.setDipendenteId(dipendente.getId());

                } else {
                    throw new BadRequestException("Impossibile trovare il dipendente che si vuole associare con id: " + newDispositivoDTO.dipendenteId());
                }

            } else {
                throw new BadRequestException("Id del dipendente non fornito per il dispositivo da assegnare");
            }

        }
        return this.dispositivoDAO.save(dispositivo);

    }


    public void findByIdAndDelete(int id) {
        Dispositivo found = this.findById(id);
        this.dispositivoDAO.delete(found);
    }


    public Dispositivo update(int id, NewDispositivoDTO newDispositivoDTO) {
        Dispositivo dispositivo = dispositivoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (newDispositivoDTO.StatoDispositivo() != null) {
            dispositivo.setStato(newDispositivoDTO.StatoDispositivo());
        }

        if (newDispositivoDTO.TipoDispositivo() != null) {
            dispositivo.setTipo(newDispositivoDTO.TipoDispositivo());
        }

        if (newDispositivoDTO.StatoDispositivo() != StatoDispositivo.ASSEGNATO) {
            dispositivo.setDipendenteId(null);
        }
        return dispositivoDAO.save(dispositivo);

    }

}
