package AlexSpring.GestioneDispositivi.controllers;


import AlexSpring.GestioneDispositivi.entities.Dipendente;
import AlexSpring.GestioneDispositivi.exceptions.BadRequestException;
import AlexSpring.GestioneDispositivi.payloads.NewDipendenteDTO;
import AlexSpring.GestioneDispositivi.payloads.NewDipendenteRespDTO;
import AlexSpring.GestioneDispositivi.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;


        //* RITORNO IL MAPPIG DI TUTTI I DIPENDENTI
    @GetMapping
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String sortBy) {

        return this.dipendenteService.getAllDipendenti(page,size,sortBy);

    }

    //* CREO IL MAPPING DI DIPENDENTI
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteRespDTO saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        else {
            return new NewDipendenteRespDTO(this.dipendenteService.save(body).getId());
        }
    }
    //* RITORNO IL MAPPIG DI  DIPENDENTI ID
    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable int dipendenteId){
        return this.dipendenteService.findById(dipendenteId); ////
    }

    //* AGGIORNO IL MAPPING DI DIPENDENTI
    @PutMapping("/{dipendenteId}")
    public NewDipendenteRespDTO update(@PathVariable int dipendenteId, @RequestBody NewDipendenteDTO body,BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException((validation.getAllErrors()));
        }
        else {

            return new NewDipendenteRespDTO(this.dipendenteService.update(dipendenteId,body).getId());
        }
    }
    //* CANCELLO IL MAPPING DI DIPENDENTI CON ID



    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int dipendenteId){
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }
}
