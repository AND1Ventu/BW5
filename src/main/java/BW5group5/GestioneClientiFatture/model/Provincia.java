package BW5group5.GestioneClientiFatture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Provincia {

    @Id
    private String sigla;

    private String provincia;

    @Override
    public String toString() {
        return "Provincia{" +
                "sigla='" + sigla + '\'' +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;
}
