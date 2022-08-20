package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.entities.enums.Departement;

import java.util.List;

public interface EmployeService {



    EmployeDTO saveEmploye(EmployeDTO employeDTO);

    EmployeDTO updateEmploye(EmployeDTO employeDTO);

    List<EmployeDTO> listEmploye();
    void deleteEmploye(int employeId);
    EmployeDTO getEmploye(int employeId) throws EmployeNotFoundException;

    List<EmployeDTO> chercherEmploye(String keyword);
}
