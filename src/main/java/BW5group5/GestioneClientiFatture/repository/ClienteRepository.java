package BW5group5.GestioneClientiFatture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import BW5group5.GestioneClientiFatture.model.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, PagingAndSortingRepository<Cliente, Integer> {

    public Optional<Cliente> deleteById(int id);
}

