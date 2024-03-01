package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
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

    public Fattura(LocalDate dataEmissione, int importo, String stato, Cliente cliente) {
        this.dataEmissione = dataEmissione;
        this.importo = importo;
        this.stato = stato;
        this.cliente = cliente;
    }
}
