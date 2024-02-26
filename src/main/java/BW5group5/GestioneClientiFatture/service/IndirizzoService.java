package BW5group5.GestioneClientiFatture.service;

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

    public Auto getIndirizzoById(int id) throws NotFoundException {
        return autoRepository.findById(id).orElseThrow(() -> new NotFoundException("Auto con id=" + id + " non trovata"));
    }

    public Auto saveAuto(AutoRequest autoRequest) throws NotFoundException {

        Persona persona = personaService.getPersonaById(autoRequest.getIdPersona());

        Auto auto = new Auto();
        auto.setAlimentazione(autoRequest.getAlimentazione());
        auto.setNome(autoRequest.getNome());
        auto.setCilindrata(autoRequest.getCilindrata());
        auto.setMarca(autoRequest.getMarca());
        auto.setPersona(persona);

        return autoRepository.save(auto);

    }

    public Auto updateAuto(int id, AutoRequest autoRequest) throws NotFoundException {
        Auto auto = getAutoById(id);

        Persona persona = personaService.getPersonaById(autoRequest.getIdPersona());

        auto.setAlimentazione(autoRequest.getAlimentazione());
        auto.setNome(autoRequest.getNome());
        auto.setCilindrata(autoRequest.getCilindrata());
        auto.setMarca(autoRequest.getMarca());
        auto.setPersona(persona);

        return autoRepository.save(auto);

    }

    public void deleteAuto(int id) throws NotFoundException {
        Auto auto = getAutoById(id);

        autoRepository.delete(auto);
    }

    public Auto uploadLogo(int id, String url) throws NotFoundException{
        Auto auto = getAutoById(id);

        auto.setLogo(url);
        return autoRepository.save(auto);
    }

}