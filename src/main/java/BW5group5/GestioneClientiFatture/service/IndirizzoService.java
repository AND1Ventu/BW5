package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.dto.IndirizzoRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Comune;
import BW5group5.GestioneClientiFatture.model.Indirizzo;
import BW5group5.GestioneClientiFatture.repository.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ComuneService comuneService;

    public Page<Indirizzo> getAll(Pageable pageable){
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo getIndirizzoById(int id) throws NotFoundException {
        return indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException("Indirizzo con id=" + id + " non trovata"));
    }

    public Indirizzo saveIndirizzo(Int idComune, IndirizzoRequest indirizzoRequest) throws NotFoundException {

        Comune comune = comuneService.findComuneById(idComune);

        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzo.getCivico());
        indirizzo.setLocalita(indirizzoRequest.getLocalita());
        indirizzo.setComune(comune);

        return indirizzoRepository.save(indirizzo);

    }

    public Indirizzo updateIndirizzo(int idComune, IndirizzoRequest indirizzoRequest) throws NotFoundException {
        Indirizzo indirizzo = getIndirizzoById(idComune);

        Comune comune = comuneService.findComuneById(idComune);

        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzo.getCivico());
        indirizzo.setLocalita(indirizzoRequest.getLocalita());
        indirizzo.setComune(comune);

        return indirizzoRepository.save(indirizzo);

    }

    public void deleteIndirizzo(int id) throws NotFoundException {
        Indirizzo indirizzo = getIndirizzoById(id);

        indirizzoRepository.delete(indirizzo);
    }


}