package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.model.Fattura;
import BW5group5.GestioneClientiFatture.repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteService clienteService;

    public List<Fattura> getAllFatture() {
        return fatturaRepository.findAll();
    }

    public Fattura getFatturaById(int id) throws NotFoundException {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }
    public Fattura saveFattura(FatturaRequest fatturaRequest) {
        Fattura fattura = new Fattura(fatturaRequest.getDataEmissione(), fatturaRequest.getImporto(), fatturaRequest.getStato(), fatturaRequest.getCliente());
        return fatturaRepository.save(fattura);
    }
    public void deleteFattura(int id) {
        Fattura fattura = getFatturaById(id);
        fatturaRepository.delete(fattura);
    }

    public Fattura updateFattura(int id, FatturaRequest fatturaRequest) {
        Fattura fattura = getFatturaById(id);
        Cliente cliente = clienteService.getClienteById(id);

        fattura.setDataEmissione(fatturaRequest.getDataEmissione());
        fattura.setImporto(fatturaRequest.getImporto());
        fattura.setStato(fatturaRequest.getStato());
        fattura.setCliente(cliente);
        return fatturaRepository.save(fattura);
    }
}
