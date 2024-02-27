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

    @Column(name = "comune", columnDefinition = "VARCHAR(255)")
    private String comune;
    private int cap;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

}

