package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.entities.Deplacement;

import java.util.Date;
import java.util.List;

public interface DeplacementService {




    Deplacement saveDeplacement(Date dateDepart, Date dateRetour, int employeId, int projetId, String adresse /*, int adresseId*/) throws EmployeNotFoundException, DeplacementNotFoundException, ProjectNotFoundException/*, AdresseNotFoundException*/;

    void deleteDeplacement(int deplacementId);
    Deplacement editDeplacement(Deplacement deplacement);
    List<DeplacementDTO> listDeplacement();

    Deplacement getDeplacement(int deplacementId) throws DeplacementNotFoundException;
}
