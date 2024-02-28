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

public interface FatturaRepository extends JpaRepository<Fattura, Integer>, PagingAndSortingRepository<Fattura, Integer> {

    Page<Fattura> findByCliente(Pageable pageable);
    Page<Fattura> findByStato(Pageable pageable);
    Page<Fattura> findByData(Pageable pageable);
    Page<Fattura> findByAnno(Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :minImporto AND :maxImporto")
    Page<Fattura> findByImportoRange(@Param("minImporto") double minImporto, @Param("maxImporto") double maxImporto);


}
