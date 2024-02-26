package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Fattura;
import BW5group5.GestioneClientiFatture.repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;

    public List<Fattura> getAllFatture() {
        return fatturaRepository.findAll();
    }

    public Fattura getFatturaById(int id) throws NotFoundException {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }
    public Fattura saveFattura(Fattura fattura) {
        return fatturaRepository.save(fattura);
    }
    public void deleteFattura(Fattura fattura) {
        fatturaRepository.delete(fattura);
    }
}
