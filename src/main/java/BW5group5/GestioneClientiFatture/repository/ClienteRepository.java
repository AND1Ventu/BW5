package BW5group5.GestioneClientiFatture.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import BW5group5.GestioneClientiFatture.model.Cliente;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, PagingAndSortingRepository<Cliente, Integer> {

    public Optional<Cliente> deleteById(int id);

    List<Cliente> findByOrderByNome(Pageable pageable);
    List<Cliente> findByOrderByFatturatoAnnuale();
    List<Cliente> findByOrderByDataInserimento();
    List<Cliente> findByOrderByDataUltimoContatto();

    List<Cliente> findByOrderBySedeLegaleProvincia();


    List<Cliente> findByFatturatoAnnualeGreaterThan(int minFatturato);
    List<Cliente> findByDataInserimentoAfter(LocalDate dataInserimento);
    List<Cliente> findByDataUltimoContattoAfter(LocalDate dataUltimoContatto);


    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :parteDelNome, '%'))")
    List<Cliente> findByParteDelNome(@Param("parteDelNome") String parteDelNome);



}

