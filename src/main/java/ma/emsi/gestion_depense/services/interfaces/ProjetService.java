package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Projet;

import java.util.Date;
import java.util.List;

public interface ProjetService {

    Projet saveProjet(String titre, Date dateDebut, Date dateFin, double montantTotal);

    void deleteprojet(int projetId);
    Projet editProjet(Projet projet);
    List<ProjetDTO> listProjet();
    Projet getProjet(int id) throws ProjectNotFoundException;
}
