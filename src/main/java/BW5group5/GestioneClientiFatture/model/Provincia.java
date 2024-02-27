package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Provincia {

    @Id
    private String sigla;

    private String provincia;

    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;
}
