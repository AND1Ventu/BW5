package BW5group5.GestioneClientiFatture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import BW5group5.GestioneClientiFatture.model.Comune;

public interface ComuneRepository extends JpaRepository<Comune, Integer>, PagingAndSortingRepository<Comune, Integer> {

    Comune findComuneById(int id);

}
