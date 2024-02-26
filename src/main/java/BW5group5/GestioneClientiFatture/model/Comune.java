package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comune")
    private int id;
    private String comune;
    private String provincia;
    private String sigla;

}
