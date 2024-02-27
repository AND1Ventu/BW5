package BW5group5.GestioneClientiFatture.repository;

import BW5group5.GestioneClientiFatture.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    public Optional<Utente> findByUsername(String username);
    void deleteById(int id);
}
