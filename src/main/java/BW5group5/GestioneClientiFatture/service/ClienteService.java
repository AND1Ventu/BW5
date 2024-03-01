package BW5group5.GestioneClientiFatture.service;

import java.time.LocalDate;
import BW5group5.GestioneClientiFatture.dto.ClienteRequest;
import BW5group5.GestioneClientiFatture.dto.IndirizzoRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.model.Indirizzo;
import BW5group5.GestioneClientiFatture.model.TipoSede;
import BW5group5.GestioneClientiFatture.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private IndirizzoService indirizzoService;

    public Cliente saveCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        mapClienteRequestToCliente(clienteRequest, cliente, true);
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
    }

    public Page<Cliente> getAllClienti(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente updateCliente(int id, ClienteRequest clienteRequest ) {
        Cliente cliente = getClienteById(id);
        mapClienteRequestToCliente(clienteRequest, cliente, false);
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }

    public Cliente uploadAvatar(int id, String url) {
        Cliente cliente = getClienteById(id);
        cliente.setLogoAziendale(url);
        return clienteRepository.save(cliente);
    }

    private void mapClienteRequestToCliente(ClienteRequest clienteRequest, Cliente cliente, boolean firstTime) {
        cliente.setRagioneSociale(clienteRequest.getRagioneSociale());
        cliente.setPartitaIva(clienteRequest.getPartitaIva());
        cliente.setEmail(clienteRequest.getEmail());
        if (firstTime) cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(clienteRequest.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteRequest.getFatturatoAnnuale());
        cliente.setPec(clienteRequest.getPec());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setEmailContatto(clienteRequest.getEmailContatto());
        cliente.setNomeContatto(clienteRequest.getNomeContatto());
        cliente.setCognomeContatto(clienteRequest.getCognomeContatto());
        cliente.setTelefonoContatto(clienteRequest.getTelefonoContatto());
        cliente.setTipoAzienda(clienteRequest.getTipoAzienda());

    }

    public Cliente setIndirizzoCliente (int id_cliente, int id_indirizzo){
       Cliente cliente = getClienteById(id_cliente);
        Indirizzo indirizzo = indirizzoService.getIndirizzoById(id_indirizzo);
        if (cliente.getIndirizzi().size() >= 2){
            throw new RuntimeException("Il cliente ha già due indirizzi");
        }
        else{
            cliente.getIndirizzi().add(indirizzo);
            return clienteRepository.save(cliente);
        }
    }


    public Page<Cliente> getAllClientiOrderedByRagioneSociale(Pageable pageable) {
        return clienteRepository.findByOrderByRagioneSociale(pageable);
    }

    public Page<Cliente> getAllClientiOrderedByFatturatoAnnuale(Pageable pageable) {
        return clienteRepository.findByOrderByFatturatoAnnuale(pageable);
    }

    public Page<Cliente> getAllClientiOrderedByDataInserimento(Pageable pageable) {
        return clienteRepository.findByOrderByDataInserimento(pageable);
    }

    public Page<Cliente> getAllClientiOrderedByDataUltimoContatto(Pageable pageable) {
        return clienteRepository.findByOrderByDataUltimoContatto(pageable);
    }

    public Page<Cliente> getAllClientiOrderedBySedeLegaleProvincia(Pageable pageable) {
        return clienteRepository.findByProvinciaSedeLegale(pageable);
    }

    public Page<Cliente> findAllClientiByFatturato(Pageable pageable) {
        return clienteRepository.findByOrderByFatturatoAnnuale(pageable);
    }

    public Page<Cliente> findAllClientiByDataInserimento( LocalDate data, Pageable pageable) {
        return clienteRepository.findByDataInserimentoAfter( data, pageable);
    }

    public Page<Cliente> findAllClientiByDataUltimoContatto( LocalDate data, Pageable pageable) {
        return clienteRepository.findByDataUltimoContattoAfter(data, pageable);
    }

    public Page<Cliente> findAllClientiByParteNome(String parteDelNome, Pageable pageable) {
        return clienteRepository.findByParteDelNome(parteDelNome, pageable);
    }
}
