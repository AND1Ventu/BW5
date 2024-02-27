package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @JoinColumn(name = "id_comune")
    private Comune comune;

    @ManyToMany(mappedBy = "indirizzi")
    private List<Cliente> clienti;
}
