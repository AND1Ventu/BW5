package BW5group5.GestioneClientiFatture.repository;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import BW5group5.GestioneClientiFatture.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import BW5group5.GestioneClientiFatture.model.Fattura;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FatturaRepository extends JpaRepository<Fattura, Integer>, PagingAndSortingRepository<Fattura, Integer> {

    Page<Fattura> findByCliente(Cliente cliente, Pageable pageable);
    Page<Fattura> findByStato(String stato, Pageable pageable);
    Page<Fattura> findByDataEmissione(LocalDate date, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.dataEmissione) = :data")
    Page<Fattura> findByAnno(@Param("data") String date, Pageable pageable);
    @Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :minImporto AND :maxImporto")
    Page<Fattura> findByImportoRange(@Param("minImporto") double minImporto, @Param("maxImporto") double maxImporto, Pageable pageable);


}
