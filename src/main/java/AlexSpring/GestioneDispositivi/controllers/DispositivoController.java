package AlexSpring.GestioneDispositivi.controllers;

import AlexSpring.GestioneDispositivi.entities.Dipendente;
import AlexSpring.GestioneDispositivi.entities.Dispositivo;
import AlexSpring.GestioneDispositivi.exceptions.BadRequestException;
import AlexSpring.GestioneDispositivi.payloads.NewDipendenteDTO;
import AlexSpring.GestioneDispositivi.payloads.NewDipendenteRespDTO;
import AlexSpring.GestioneDispositivi.payloads.NewDispositivoDTO;
import AlexSpring.GestioneDispositivi.payloads.NewDispositivoRespDTO;
import AlexSpring.GestioneDispositivi.repositories.DispositivoDAO;
import AlexSpring.GestioneDispositivi.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;


    @GetMapping
    public Page<Dispositivo> getAllDispositivi(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String sortBy){
        return this.dispositivoService.getAllDispositivi(page,size,sortBy);
    }


    @GetMapping("/{dipendenteId}")
    public Dispositivo findById(@PathVariable int dipendenteId){
        return this.dispositivoService.findById(dipendenteId);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDispositivoRespDTO create(@RequestBody @Validated NewDispositivoDTO body, BindingResult validation){
        if (validation.hasErrors()){
            System.out.println("ERRORI" + body);
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewDispositivoRespDTO(this.dispositivoService.create(body).getId());

    }


    @DeleteMapping("/{dispositivoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int dispositivoId){
        Dispositivo dispositivo = dispositivoService.findById(dispositivoId);
        dispositivo.setDipendenteId(0);


        this.dispositivoService.findByIdAndDelete(dispositivo.getId());
    }


    @PutMapping("/{dispositivoId}")
    public NewDispositivoRespDTO update(@PathVariable int dispositivoId, @RequestBody NewDispositivoDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException((validation.getAllErrors()));
        }
        else {

            return new NewDispositivoRespDTO(this.dispositivoService.update(dispositivoId,body).getId());
        }
    }


}
