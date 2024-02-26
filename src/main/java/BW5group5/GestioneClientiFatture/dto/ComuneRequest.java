package BW5group5.GestioneClientiFatture.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComuneRequest {

    @NotBlank(message = "Il campo 'comune' non può essere vuoto")
    private String comune;

    @NotBlank(message = "Il campo 'provincia' non può essere vuoto")
    private String provincia;

    @NotBlank(message = "Il campo 'sigla' non può essere vuoto")
    private String sigla;

}
