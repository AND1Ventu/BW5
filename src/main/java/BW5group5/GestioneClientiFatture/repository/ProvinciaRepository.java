package BW5group5.GestioneClientiFatture.repository;

import BW5group5.GestioneClientiFatture.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia, String> {

    Provincia findProvinciaBySigla(String sigla);
}

