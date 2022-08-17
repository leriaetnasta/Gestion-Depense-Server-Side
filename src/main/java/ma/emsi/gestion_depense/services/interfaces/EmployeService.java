package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.entities.enums.Departement;

import java.util.List;

public interface EmployeService {



    Employe saveEmploye(EmployeDTO employeDTO);

    Employe editEmploye(Employe employe);
    List<EmployeDTO> listEmploye();
    void deleteEmploye(int employeId);
    Employe getEmploye(int employeId) throws EmployeNotFoundException;
    Employe chercherEmploye(Employe employe);

}
