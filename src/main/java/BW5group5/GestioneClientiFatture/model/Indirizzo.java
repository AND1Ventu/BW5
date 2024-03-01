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
    private TipoSede tipoSede;


    @ManyToOne
    @JoinColumn(name = "id_comune")
    private Comune comune;

    @Override
    public String toString() {
        return "Indirizzo{" +
                "idIndirizzo=" + idIndirizzo +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", localita='" + localita + '\'' +
                ", tipoSede=" + tipoSede +
                ", comune=" + comune.getComune() +
                '}';
    }

    @ManyToMany(mappedBy = "indirizzi")
    private List<Cliente> clienti;
}
