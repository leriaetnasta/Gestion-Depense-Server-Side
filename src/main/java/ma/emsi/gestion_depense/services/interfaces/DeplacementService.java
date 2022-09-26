package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.entities.Deplacement;

import java.util.Date;
import java.util.List;

public interface DeplacementService {


    DeplacementDTO saveDeplacement(DeplacementDTO deplacementDTO, int idP, int idE, int idD) throws DeplacementNotFoundException, ProjectNotFoundException, EmployeNotFoundException, DepenseNotFoundException;

    void deleteDeplacement(int deplacementId);

    DeplacementDTO updateDeplacement(DeplacementDTO deplacementDTO, int idP, int idE, int idD) throws ProjectNotFoundException, EmployeNotFoundException, DepenseNotFoundException;

    List<DeplacementDTO> listDeplacement();

    DeplacementDTO getDeplacement(int deplacementId) throws DeplacementNotFoundException;

}
