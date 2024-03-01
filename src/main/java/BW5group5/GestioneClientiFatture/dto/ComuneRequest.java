package BW5group5.GestioneClientiFatture.dto;

import BW5group5.GestioneClientiFatture.model.Provincia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComuneRequest {

    @NotBlank(message = "Il campo 'comune' non può essere vuoto")
    private String comune;

    @NotNull(message = "Il campo 'cap' non può essere vuoto")
    private int cap;

    @NotNull(message = "Il campo 'provincia' non può essere vuoto")
    private int idProvincia;
}
