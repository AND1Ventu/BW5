package BW5group5.GestioneClientiFatture.dto;

import BW5group5.GestioneClientiFatture.model.Tipologia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteRequest {
    @NotBlank(message = "Username obbligatorio")
    private String username;
    @NotBlank(message = "Password obbligatoria")
    private String password;
    @NotBlank(message = "Email obbligatoria")
    private String email;
    @NotBlank(message = "Nome obbligatorio")
    private String nome;
    @NotBlank(message = "Cognome obbligatorio")
    private String cognome;
    @NotNull(message = "Tipologia obbligatoria")
    private Tipologia tipologia;
}
