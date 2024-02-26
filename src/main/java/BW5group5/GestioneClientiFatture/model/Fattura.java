package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroFattura;
    private LocalDate dataEmissione;
    private int importo;
    private String stato;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
