package BW5group5.GestioneClientiFatture.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import BW5group5.GestioneClientiFatture.model.TipoAzienda;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClienteRequest {

    @NotBlank(message = "Il campo 'ragioneSociale' non può essere vuoto")
    @Size(min = 3, max = 100, message = "Il campo 'ragioneSociale' deve essere lungo tra 3 e 100 caratteri")
    private String ragioneSociale;

    @NotBlank(message = "Il campo 'partitaIva' non può essere vuoto")
    private String partitaIva;

    @NotBlank(message = "Il campo 'email' non può essere vuoto")
    private String email;

    @NotNull(message = "Il campo 'dataInserimento' non può essere vuoto")
    private LocalDate dataInserimento;

    @NotNull(message = "Il campo 'dataUltimoContatto' non può essere vuoto")
    private LocalDate dataUltimoContatto;

    @NotNull(message = "Il campo 'fatturatoAnnuale' non può essere vuoto")
    private int fatturatoAnnuale;

    @NotBlank(message = "Il campo 'pec' non può essere vuoto")
    private String pec;

    @NotBlank(message = "Il campo 'telefono' non può essere vuoto")
    private String telefono;

    @NotBlank(message = "Il campo 'emailContatto' non può essere vuoto")
    private String emailContatto;

    @NotBlank(message = "Il campo 'nomeContatto' non può essere vuoto")
    private String nomeContatto;

    @NotBlank(message = "Il campo 'cognomeContatto' non può essere vuoto")
    private String cognomeContatto;

    @NotBlank(message = "Il campo 'telefonoContatto' non può essere vuoto")
    private String telefonoContatto;

    @NotBlank(message = "Il campo 'logoAziendale' non può essere vuoto")
    private String logoAziendale;

    @NotNull(message = "Il campo 'tipoAzienda' non può essere vuoto")
    private TipoAzienda tipoAzienda;

    @OneToMany(mappedBy = "indirizzi")
    private List<IndirizzoRequest> indirizzi;

    @OneToMany(mappedBy = "fatture")
    private List<FatturaRequest> fatture;


}
