package BW5group5.GestioneClientiFatture.repository;

import BW5group5.GestioneClientiFatture.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    public Optional<Utente> findByUsername(String username);
    public Optional<Utente> deleteByUsername(String username);
}
