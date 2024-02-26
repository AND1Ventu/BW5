package BW5group5.GestioneClientiFatture.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_utente")
    private int idUtente;

    @Column(unique = true)
    private String username;

    private String passwordHashed;
    private String email;
    private String nome;
    private String cognome;
    private String avatar;

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;
}