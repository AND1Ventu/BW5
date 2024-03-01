package BW5group5.GestioneClientiFatture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IndirizzoRequest {

    @NotBlank(message = "Il campo 'via' non può essere vuoto")
    private String via;

    @NotBlank(message = "Il campo 'civico' non può essere vuoto")
    private String civico;

    @NotBlank(message = "Il campo 'localita' non può essere vuoto")
    private String localita;

    @NotNull(message = "Il campo 'comune' non può essere vuoto")
    private int idComune;

}

