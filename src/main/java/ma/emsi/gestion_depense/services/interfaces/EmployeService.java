package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.entities.enums.Departement;

import java.util.List;

public interface EmployeService {





    EmployeDTO saveEmploye(EmployeDTO employeDTO, int idP, int idD) throws ProjectNotFoundException, DeplacementNotFoundException;

    EmployeDTO addProjetToEmploye(EmployeDTO employeDTO, ProjetDTO projetDTO);

    EmployeDTO addDeplacementToEmploye(EmployeDTO employeDTO, DeplacementDTO deplacementDTO);


    List<EmployeDTO> listEmploye();
    void deleteEmploye(int employeId);
    Employe getEmploye(int employeId) throws EmployeNotFoundException;

    List<EmployeDTO> chercherEmploye(String keyword);

    EmployeDTO updateEmploye(int id,EmployeDTO employeDTO, int idP, int idD) throws ProjectNotFoundException, DeplacementNotFoundException;
}
