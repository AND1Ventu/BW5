package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.dto.ClienteRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).orElseThrow(()->new NotFoundException("Cliente non trovato"));
    }

    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
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
}

