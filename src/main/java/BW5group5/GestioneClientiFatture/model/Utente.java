package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUtente;
    private String username;
    private String passwordHashed;
    private String email;
    private String nome;
    private String cognome;
    private String avatar;
    private Tipologia tipologia;
}