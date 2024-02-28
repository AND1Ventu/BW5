package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.dto.ClienteRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        mapClienteRequestToCliente(clienteRequest, cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
    }

    public Page<Cliente> getAllClienti(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente updateCliente(int id, ClienteRequest clienteRequest) {
        Cliente cliente = getClienteById(id);
        mapClienteRequestToCliente(clienteRequest, cliente);
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

    private void mapClienteRequestToCliente(ClienteRequest clienteRequest, Cliente cliente) {
        cliente.setRagioneSociale(clienteRequest.getRagioneSociale());
        cliente.setPartitaIva(clienteRequest.getPartitaIva());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setDataInserimento(clienteRequest.getDataInserimento());
        cliente.setDataUltimoContatto(clienteRequest.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteRequest.getFatturatoAnnuale());
        cliente.setPec(clienteRequest.getPec());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setEmailContatto(clienteRequest.getEmailContatto());
        cliente.setNomeContatto(clienteRequest.getNomeContatto());
        cliente.setCognomeContatto(clienteRequest.getCognomeContatto());
        cliente.setTelefonoContatto(clienteRequest.getTelefonoContatto());
        cliente.setLogoAziendale(clienteRequest.getLogoAziendale());
        cliente.setTipoAzienda(clienteRequest.getTipoAzienda());
        cliente.setIndirizzi(clienteRequest.getIndirizzi());
        cliente.setFatture(clienteRequest.getFatture());
    }


    public Page<Cliente> getAllClientiOrderedByName(Pageable pageable) {
        return (Page<Cliente>) clienteRepository.findByOrderByNome(pageable);
    }

    public Page<Cliente> getAllClientiOrderedByFatturatoAnnuale(Pageable pageable) {
        return (Page<Cliente>) clienteRepository.findByOrderByFatturatoAnnuale(pageable);
    }

    public Page<Cliente> getAllClientiOrderedByDataInserimento(Pageable pageable) {
        return (Page<Cliente>) clienteRepository.findByOrderByDataInserimento(pageable);
    }

    public Page<Cliente> getAllClientiOrderedByDataUltimoContatto(Pageable pageable) {
        return (Page<Cliente>) clienteRepository.findByOrderByDataUltimoContatto(pageable);
    }

    public Page<Cliente> getAllClientiOrderedBySedeLegaleProvincia(Pageable pageable) {
        return (Page<Cliente>) clienteRepository.findByOrderBySedeLegaleProvincia(pageable);
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
}
