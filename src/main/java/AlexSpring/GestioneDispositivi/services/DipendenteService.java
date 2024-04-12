package AlexSpring.GestioneDispositivi.services;

import AlexSpring.GestioneDispositivi.entities.Dipendente;
import AlexSpring.GestioneDispositivi.exceptions.BadRequestException;
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
    public Page<Dipendente> getAllDipendenti(int page, int size,String sortBy){
        if (size>100) size= 100;

        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));

        return this.dipendenteDAO.findAll(pageable);

    }

       //* SALVO I DIPENDENTI

    public Dipendente save(NewDipendenteDTO dipendenteDTO){
        this.dipendenteDAO.findByEmail(dipendenteDTO.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'email "+ dipendente.getEmail()+ " è già in uso");
        });

        Dipendente dipendente= new Dipendente(dipendenteDTO.username(),dipendenteDTO.name(),dipendenteDTO.surname(),dipendenteDTO.email());

      return   this.dipendenteDAO.save(dipendente);
    }


}
