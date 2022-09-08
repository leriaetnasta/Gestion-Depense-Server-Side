package ma.emsi.gestion_depense.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.Depense;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.entities.Projet;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DeplacementDTO {
    private int id;

    String adresse;
    private Date dateDepart;
    private Date dateRetour;

    private Projet projet;
    private EmployeDTO employe;
    private List<DepenseDTO> listdepense=new ArrayList<>();



}
