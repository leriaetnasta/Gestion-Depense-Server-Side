package ma.emsi.gestion_depense.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.Client;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.entities.Employe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class ProjetDTO {
    private int id;

    private String titre;
    private double MontantTotal;
    private Date dateDebut;
    private Date dateFin;


    private List<Employe> listEmploye= new ArrayList<Employe>();

}
