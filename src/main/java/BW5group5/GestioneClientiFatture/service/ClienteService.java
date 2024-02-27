package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.dto.ClienteRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.repository.ClienteRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(ClienteRequest clienteRequest) {
        return clienteRepository.save(clienteRequest);
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).orElseThrow(()->new NotFoundException("Cliente non trovato"));
    }

    public Page<Cliente> getAllClienti(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente updateCliente(int id, ClienteRequest clienteRequest) {
            Cliente cliente = getClienteById(id);

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
            return clienteRepository.save(cliente);
        }
    public void deleteCliente(int id) {
        clienteRepository.deleteById(id).orElseThrow(()->new NotFoundException("Cliente non trovato"));
    }

    public Cliente uploadAvatar(int id, String url){
        Cliente cliente = getClienteById(id);
        cliente.setLogoAziendale(url);
        return clienteRepository.save(cliente);
    }
}

