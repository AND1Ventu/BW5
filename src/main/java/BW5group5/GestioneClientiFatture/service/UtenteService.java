package BW5group5.GestioneClientiFatture.service;

import BW5group5.GestioneClientiFatture.dto.UtenteRequest;
import BW5group5.GestioneClientiFatture.exception.NotFoundException;
import BW5group5.GestioneClientiFatture.model.Tipologia;
import BW5group5.GestioneClientiFatture.model.Utente;
import BW5group5.GestioneClientiFatture.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder encoder;

    public Utente saveUtente(UtenteRequest utenteRequest) {
        Utente utente = new Utente();

        utente.setUsername(utenteRequest.getUsername());
        utente.setPasswordHashed(encoder.encode(utenteRequest.getPasswordHashed()));
        utente.setEmail(utenteRequest.getEmail());
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setAvatar(utenteRequest.getAvatar());
        utente.setTipologia(Tipologia.USER);

        return utenteRepository.save(utente);
    }
    public Utente getUtenteById(int id){
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException("Utente non trovato"));
    }

    public Utente getUtenteByUsername(String username){
        return utenteRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Username non trovato"));
    }

    public List<Utente> getAllUtenti(){
        return utenteRepository.findAll();
    }
    public Utente updateUtente(int id, UtenteRequest utenteRequest){
        Utente utente = getUtenteById(id);

        utente.setUsername(utenteRequest.getUsername());
        utente.setPasswordHashed(utenteRequest.getPasswordHashed());
        utente.setEmail(utenteRequest.getEmail());
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setAvatar(utenteRequest.getAvatar());

        return utenteRepository.save(utente);
    }

    public Utente updateTipologiaUtente(int id,String tipologia){
        Utente utente = getUtenteById(id);

        utente.setTipologia(Tipologia.valueOf(tipologia));
        return utenteRepository.save(utente);
    }

    public void deleteUtente(int id){
        utenteRepository.deleteById(id).orElseThrow(()->new NotFoundException("Utente non trovato"));
    }
}