package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indirizzo")
    private int idIndirizzo;
    private String via;
    private String civico;
    private String localita;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Comune comune;
}
