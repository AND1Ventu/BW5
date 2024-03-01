package BW5group5.GestioneClientiFatture.controller;

import BW5group5.GestioneClientiFatture.dto.ClienteRequest;
import BW5group5.GestioneClientiFatture.exception.BadRequestException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.model.TipoSede;
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

    @GetMapping("/clienti/sorted/name")
    public Page<Cliente> getAllClientiOrderedByName(Pageable pageable) {
        return clienteService.getAllClientiOrderedByRagioneSociale(pageable);
    }

    @GetMapping("/clienti/sorted/fatturato")
    public Page<Cliente> getAllClientiOrderedByFatturato(Pageable pageable) {
        return clienteService.getAllClientiOrderedByFatturatoAnnuale(pageable);
    }

    @GetMapping("/clienti/sorted/dataInserimento")
    public Page<Cliente> getAllClientiOrderedByDataInserimento(Pageable pageable) {
        return clienteService.getAllClientiOrderedByDataInserimento(pageable);
    }

    @GetMapping("/clienti/sorted/dataUltiimoContatto")
    public Page<Cliente> getAllClientiOrderedByDataUltimoContatto(Pageable pageable) {
        return clienteService.getAllClientiOrderedByDataUltimoContatto(pageable);
    }

    @GetMapping("/clienti/sorted/sedeLegaleProvincia")
    public Page<Cliente> getAllClientiOrderedBySedeLegaleProvincia( Pageable pageable) {
        return clienteService.getAllClientiOrderedBySedeLegaleProvincia( pageable);
    }

    @PatchMapping("/clienti/setIndirizzo")
    public Cliente setIndirizzoCliente(@RequestParam("id_cliente") int id_cliente, @RequestParam("id_indirizzo") int id_indirizzo){
        return clienteService.setIndirizzoCliente(id_cliente,id_indirizzo);
    }


    @GetMapping("/clienti/find/fatturato")
    public Page<Cliente> findAllClientiByFatturato(Pageable pageable) {
        return clienteService.findAllClientiByFatturato(pageable);
    }

    @GetMapping("/clienti/find/dataInserimento")
    public Page<Cliente> findAllClientiByDataInserimento( @RequestParam("data") LocalDate data, Pageable pageable) {
        return clienteService.findAllClientiByDataInserimento( data, pageable);
    }

    @GetMapping("/clienti/find/dataUltimoContatto")
    public Page<Cliente> findAllClientiByDataUltimoContatto(@RequestParam("data") LocalDate data,Pageable pageable) {
        return clienteService.findAllClientiByDataUltimoContatto( data,pageable);
    }

    @GetMapping("/clienti/nome")
    public Page<Cliente> findAllClientiByParteNome(@RequestParam("parteDelNome") String parteDelNome, Pageable pageable) {
        return clienteService.findAllClientiByParteNome(parteDelNome, pageable);
    }
}