package BW5group5.GestioneClientiFatture.controller;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import BW5group5.GestioneClientiFatture.exception.BadRequestException;
import BW5group5.GestioneClientiFatture.model.Fattura;
import BW5group5.GestioneClientiFatture.service.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping("/fatture")
    public List<Fattura> getAll() {
        return fatturaService.getAllFatture();
    }

    @GetMapping("/fatture/{id}")
    public Fattura getFatturaById(@PathVariable int id) {
        return fatturaService.getFatturaById(id);
    }

    @PutMapping("/fatture/{id}")
    public Fattura updateFattura(@PathVariable int id, @RequestBody @Validated FatturaRequest fatturaRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return fatturaService.updateFattura(id, fatturaRequest);

    }

    @PostMapping("/fatture/{id}")
    public Fattura saveFattura(@RequestBody @Validated FatturaRequest fatturaRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return fatturaService.saveFattura(fatturaRequest);
    }
    @DeleteMapping("/fatture/{id}")
    public void deleteFattura(@PathVariable int id){
        fatturaService.deleteFattura(id);
    }
}
