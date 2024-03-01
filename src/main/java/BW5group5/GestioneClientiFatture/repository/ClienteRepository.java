package BW5group5.GestioneClientiFatture.repository;

import BW5group5.GestioneClientiFatture.model.TipoSede;
import org.springframework.data.domain.Page;
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

      Page<Cliente> findByOrderByRagioneSociale(Pageable pageable);
//    Page<Cliente> findByOrderByFatturatoAnnuale(Pageable pageable);
//    Page<Cliente> findByOrderByDataInserimento(Pageable pageable);
//    Page<Cliente> findByOrderByDataUltimoContatto(Pageable pageable);
//
//    Page<Cliente> findByFatturatoAnnualeGreaterThan(int minFatturato);
//    Page<Cliente> findByDataInserimentoAfter(LocalDate dataInserimento, Pageable pageable);
//    Page<Cliente> findByDataUltimoContattoAfter(LocalDate dataUltimoContatto, Pageable pageable);


    @Query("SELECT c\n" +
        "FROM Cliente c\n" +
        "JOIN c.indirizzi i\n" +
        "JOIN i.comune co\n" +
        "JOIN co.provincia p\n" +
        "WHERE i.tipoSede = :sede_legale\n" +
        "ORDER BY p.provincia")
    Page<Cliente> findByProvinciaSedeLegale (@Param("sede_legale") TipoSede tipoSede, Pageable pageable);
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.ragioneSociale) LIKE LOWER(CONCAT('%', :parteDelNome, '%'))")
    Page<Cliente> findByParteDelNome(@Param("parteDelNome") String parteDelNome, Pageable pageable);



}

