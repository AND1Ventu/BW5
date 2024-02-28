package BW5group5.GestioneClientiFatture.controller;

import BW5group5.GestioneClientiFatture.dto.ClienteRequest;
import BW5group5.GestioneClientiFatture.exception.BadRequestException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.service.ClienteService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;  // Correct import
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/clienti")
    public Page<Cliente> getAll(Pageable pageable) {
        return clienteService.getAllClienti(pageable);
    }

    @GetMapping("/clienti/{id}")
    public Cliente getClienteById(@PathVariable int id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping("/clienti")
    public Cliente saveCliente(@RequestBody @Validated ClienteRequest clienteRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return clienteService.saveCliente(clienteRequest);
    }

    @PutMapping("/clienti/{id}")
    public Cliente updateCliente(@PathVariable int id, @RequestBody @Validated ClienteRequest clienteRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return clienteService.updateCliente(id, clienteRequest);
    }

    @DeleteMapping("/clienti/{id}")
    public void deleteCliente(@PathVariable int id) {
        clienteService.deleteCliente(id);
    }

    @PatchMapping("/clienti/{id}/upload")
    public Cliente uploadAvatar(@PathVariable int id, @RequestParam("upload") MultipartFile file) throws IOException {
        return clienteService.uploadAvatar(id,
                (String) cloudinary.uploader().upload(file.getBytes(), new HashMap<>()).get("url"));
    }

    @GetMapping("/clienti/ordered/name")
    public Page<Cliente> getAllClientiOrderedByName(Pageable pageable) {
        return clienteService.getAllClientiOrderedByName(pageable);
    }

    @GetMapping("/clienti/ordered/fatturato")
    public Page<Cliente> getAllClientiOrderedByFatturato(Pageable pageable) {
        return clienteService.getAllClientiOrderedByFatturatoAnnuale(pageable);
    }

    @GetMapping("/clienti/ordered/dataInserimento")
    public Page<Cliente> getAllClientiOrderedByDataInserimento(Pageable pageable) {
        return clienteService.getAllClientiOrderedByDataInserimento(pageable);
    }

    @GetMapping("/clienti/ordered/dataUltiimoContatto")
    public Page<Cliente> getAllClientiOrderedByDataUltimoContatto(Pageable pageable) {
        return clienteService.getAllClientiOrderedByDataUltimoContatto(pageable);
    }

    @GetMapping("/clienti/ordered/sedeLegaleProvincia")
    public Page<Cliente> getAllClientiOrderedBySedeLegaleProvincia(Pageable pageable) {
        return clienteService.getAllClientiOrderedBySedeLegaleProvincia(pageable);
    }
}


public Page<Cliente> findAllClientiByFatturato(Pageable pageable) {
    return (Page<Cliente>) clienteRepository.findByOrderByFatturatoAnnuale(pageable);
}

public Page<Cliente> findAllClientiByDataInserimento(LocalDate dataInserimento, Pageable pageable) {
    return (Page<Cliente>) clienteRepository.findByDataInserimentoAfter(dataInserimento, pageable);
}

public Page<Cliente> findAllClientiByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable) {
    return (Page<Cliente>) clienteRepository.findByDataUltimoContattoAfter(dataUltimoContatto, pageable);
}

public Page<Cliente> findAllClientiByParteNome(String parteDelNome, Pageable pageable) {
    return (Page<Cliente>) clienteRepository.findByParteDelNome(parteDelNome, pageable);
}