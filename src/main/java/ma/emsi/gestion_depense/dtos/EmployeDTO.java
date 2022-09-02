package ma.emsi.gestion_depense.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.entities.enums.Departement;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class EmployeDTO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Departement departement;
    private String phoneNumber;
    private String matricule;


}
