package BW5group5.GestioneClientiFatture.controller;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import BW5group5.GestioneClientiFatture.exception.BadRequestException;
import BW5group5.GestioneClientiFatture.model.Fattura;
import BW5group5.GestioneClientiFatture.service.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping("/fatture")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Fattura> getAll(Pageable pageable){return fatturaService.getAllFatture(pageable);}

    @GetMapping("/fatture/{id}")
    public Fattura getFatturaById(@PathVariable int id){return fatturaService.getFatturaById(id);}

    @PostMapping("/fatture")
    public Fattura saveFattura(@RequestBody @Validated FatturaRequest fatturaRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return fatturaService.saveFattura(fatturaRequest);
    }

    @PutMapping("/fatture/{id}")
    public Fattura updateFattura(
            @PathVariable int id,
            @RequestBody @Validated FatturaRequest fatturaRequest,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return fatturaService.aggiornaFattura(id, autoreRequest);
    }

    @DeleteMapping("/fatture/{id}")
    public void deleteFattura(@PathVariable int id){
        fatturaService.cancellaFattura(id);
    }

}