package BW5group5.GestioneClientiFatture.controller;

import BW5group5.GestioneClientiFatture.dto.IndirizzoRequest;
import BW5group5.GestioneClientiFatture.exception.BadRequestException;
import BW5group5.GestioneClientiFatture.model.Indirizzo;
import BW5group5.GestioneClientiFatture.service.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("/indirizzi")
    public List<Indirizzo> getAll() {
        return indirizzoService.getAll();
    }
    @GetMapping("/indirizzi/{id}")
    public Indirizzo getIndirizzoById(@PathVariable int idComune) {
        return indirizzoService.getIndirizzoById(idComune);
    }
    @PutMapping("/indirizzi/{id}")
    public Indirizzo updateIndirizzo(@PathVariable int idComune, @RequestBody @Validated IndirizzoRequest indirizzoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return indirizzoService.updateIndirizzo(idComune, indirizzoRequest);
    }
    @PostMapping("/indirizzi/{id}")
    public Indirizzo saveIndirizzo(@PathVariable int idComune, @RequestBody @Validated IndirizzoRequest indirizzoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return indirizzoService.saveIndirizzo(idComune, indirizzoRequest);
    }
    @DeleteMapping("/indirizzi/{id}")
    public void deleteIndirizzi(@PathVariable int id){
        indirizzoService.deleteIndirizzo(id);
    }
}
