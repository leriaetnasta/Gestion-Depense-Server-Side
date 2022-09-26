package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Projet;

import java.util.Date;
import java.util.List;

public interface ProjetService {


    ProjetDTO saveProjet(ProjetDTO projetDTO, int idD, int idC, int idE) throws EmployeNotFoundException, DeplacementNotFoundException,ClientNotFoundException;

    void deleteprojet(int projetId);

    List<ProjetDTO> chercherProjet(String keyword);


    ProjetDTO updateProjet(ProjetDTO projetDTO, int idD, int idC, int idE) throws EmployeNotFoundException, DeplacementNotFoundException, ClientNotFoundException;

    List<ProjetDTO> listProjet();
    ProjetDTO getProjet(int id) throws ProjectNotFoundException;

    List<ProjetDTO> projetOfClientId(int clientId)  throws ClientNotFoundException;
}
