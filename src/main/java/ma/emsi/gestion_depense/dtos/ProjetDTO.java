package ma.emsi.gestion_depense.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class ProjetDTO {
    private int id;

    private String titre;
    private double MontantTotal;
    private Date dateDebut;
    private Date dateFin;

}
