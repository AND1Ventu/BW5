package BW5group5.GestioneClientiFatture.dto;

import BW5group5.GestioneClientiFatture.model.Cliente;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FatturaRequest {

    @NotNull(message = "Il campo 'dataEmissione' non può essere vuoto")
    private LocalDate dataEmissione;

    @NotNull(message = "Il campo 'importo' non può essere vuoto")
    private int importo;

    @NotNull(message = "Il campo 'stato' non può essere vuoto")
    private String stato;


    @NotNull(message = "Il campo 'cliente' non può essere vuoto")
    private int idCliente;

}
