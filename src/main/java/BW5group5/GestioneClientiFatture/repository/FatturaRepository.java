package BW5group5.GestioneClientiFatture.repository;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import BW5group5.GestioneClientiFatture.model.Fattura;

import java.awt.print.Pageable;

public interface FatturaRepository extends JpaRepository<Fattura, Integer>, PagingAndSortingRepository<Fattura, Integer> {
}
