package BW5group5.GestioneClientiFatture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import BW5group5.GestioneClientiFatture.model.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Integer>, PagingAndSortingRepository<Indirizzo, Integer> {

}
