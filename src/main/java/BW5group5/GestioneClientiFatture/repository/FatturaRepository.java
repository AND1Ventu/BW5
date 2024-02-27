package BW5group5.GestioneClientiFatture.repository;

import BW5group5.GestioneClientiFatture.dto.FatturaRequest;
import BW5group5.GestioneClientiFatture.model.Cliente;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import BW5group5.GestioneClientiFatture.model.Fattura;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FatturaRepository extends JpaRepository<Fattura, Integer>, PagingAndSortingRepository<Fattura, Integer> {

    List<Fattura> findByCliente(Cliente cliente);
    List<Fattura> findByStato(String stato);
    List<Fattura> findByData(LocalDate data);
    List<Fattura> findByAnno(int anno);

    @Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :minImporto AND :maxImporto")
    List<Fattura> findByImportoRange(@Param("minImporto") BigDecimal minImporto, @Param("maxImporto") BigDecimal maxImporto);


}
