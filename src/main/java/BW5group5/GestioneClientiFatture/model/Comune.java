package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String comune;
    private int cap;

    @ManyToOne
    @JoinColumn(name = "sigla", referencedColumnName = "sigla")
    private Provincia provincia;

}
