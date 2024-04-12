package AlexSpring.GestioneDispositivi.services;

import AlexSpring.GestioneDispositivi.entities.Dipendente;
import AlexSpring.GestioneDispositivi.exceptions.BadRequestException;
import AlexSpring.GestioneDispositivi.exceptions.NotFoundException;
import AlexSpring.GestioneDispositivi.payloads.NewDipendenteDTO;
import AlexSpring.GestioneDispositivi.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteDAO dipendenteDAO;


    //* RITORNO TUTTI I DIPENDENTI CON PAGINAZIONE
    public Page<Dipendente> getAllDipendenti(int page, int size, String sortBy) {
        if (size > 100) size = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return this.dipendenteDAO.findAll(pageable);

    }

    //* SALVO I DIPENDENTI

    public Dipendente save(NewDipendenteDTO dipendenteDTO) {
        this.dipendenteDAO.findByEmail(dipendenteDTO.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'email " + dipendente.getEmail() + " è già in uso");
        });

        Dipendente dipendente = new Dipendente(dipendenteDTO.username(), dipendenteDTO.name(), dipendenteDTO.surname(), dipendenteDTO.email());

        return this.dipendenteDAO.save(dipendente);
    }

    public Dipendente findById(int id) {
        return this.dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Dipendente update(int id, NewDipendenteDTO dipendenteDTO) {
        Dipendente dipendeteAgg = dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (!dipendenteDTO.email().equals(dipendeteAgg.getEmail()) && dipendenteDAO.findByEmail(dipendenteDTO.email()).isPresent()) {

            throw new BadRequestException("L'email " + dipendenteDTO.email() + " è già in uso");
        }

        if (dipendenteDTO.username() != null) {
            dipendeteAgg.setUsername(dipendenteDTO.username());
        }
        if (dipendenteDTO.name() != null) {
            dipendeteAgg.setName(dipendenteDTO.name());
        }
        if (dipendenteDTO.surname() != null) {
            dipendeteAgg.setSurname(dipendenteDTO.surname());
        }
        if (dipendenteDTO.email() != null) {
            dipendeteAgg.setEmail(dipendenteDTO.email());
        }

        return dipendenteDAO.save(dipendeteAgg);

    }


    public void findByIdAndDelete(int id) {
        Dipendente found = this.findById(id);

        this.dipendenteDAO.delete(found);
    }
}
