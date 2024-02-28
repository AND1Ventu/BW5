package BW5group5.GestioneClientiFatture.controller;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import BW5group5.GestioneClientiFatture.dto.UtenteRequest;
import BW5group5.GestioneClientiFatture.exception.BadRequestException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.model.Fattura;
import BW5group5.GestioneClientiFatture.model.Utente;
import BW5group5.GestioneClientiFatture.service.FatturaService;
import BW5group5.GestioneClientiFatture.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/fatture")
    public Page<Fattura> getAll(Pageable pageable) {
        return fatturaService.getAllFatture(pageable);
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

    @GetMapping("/fatture/find/stato")
    public Page<Fattura> findFatturaByStato(@RequestParam("stato") String stato, Pageable pageable) {
        return  fatturaService.findByStato(stato, pageable);
    }

    @GetMapping("/fatture/find/data")
    public Page<Fattura> findFatturaByDataEmissione(@RequestParam("data") LocalDate data, Pageable pageable) {
        return (Page<Fattura>) fatturaService.findByDataEmissione(data, pageable);
    }

    @GetMapping("/fatture/find/anno")
    public Page<Fattura> findFatturaByAnno(@RequestParam("anno") LocalDate anno, Pageable pageable) {
        return (Page<Fattura>) fatturaService.findByAnno(anno, pageable);
    }

    @GetMapping("/fatture/find/importoRange")
    public Page<Fattura> findFatturaByImportoRange(@RequestParam("min") double min, @RequestParam("max") double max, Pageable pageable) {
        return (Page<Fattura>) fatturaService.findByImportoRange(min, max, pageable);
    }


}
