package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Cliente;
import BW5group5.GestioneClientiFatture.model.Fattura;
import BW5group5.GestioneClientiFatture.repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteService clienteService;

    public Page<Fattura> getAllFatture(Pageable pageable) {
        return fatturaRepository.findAll(pageable);
    }

    public Fattura getFatturaById(int id) throws NotFoundException {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }
    public Fattura saveFattura(FatturaRequest fatturaRequest) {
        Fattura fattura = new Fattura();
        Cliente cliente = clienteService.getClienteById(fatturaRequest.getIdCliente());
        fattura.setDataEmissione(fatturaRequest.getDataEmissione());
        fattura.setImporto(fatturaRequest.getImporto());
        fattura.setStato(fatturaRequest.getStato());
        fattura.setCliente(cliente);
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

    public Page<Fattura> findByCliente(int id, Pageable pageable) {
        Cliente cliente = clienteService.getClienteById(id);
        return fatturaRepository.findByCliente(cliente, pageable);
    }

    public Page<Fattura> findByStato(String stato, Pageable pageable) {
        return fatturaRepository.findByStato(stato, pageable);
    }

    public Page<Fattura> findByDataEmissione(LocalDate data, Pageable pageable) {
        return fatturaRepository.findByDataEmissione(data, pageable);
    }

    public Page<Fattura> findByAnno(String data, Pageable pageable) {
        return fatturaRepository.findByAnno(data, pageable);
    }

    public Page<Fattura> findByImportoRange(double min, double max, Pageable pageable){
        return fatturaRepository.findByImportoRange(min, max, pageable);
    }
}

