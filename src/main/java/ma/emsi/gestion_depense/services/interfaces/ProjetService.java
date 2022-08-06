package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Projet;

import java.util.Date;
import java.util.List;

public interface ProjetService {

    ProjetDTO saveProjet(ProjetDTO projetDTO) throws ProjectNotFoundException;

    void deleteprojet(int projetId);
    ProjetDTO updateProjet(ProjetDTO projetDTO);

    List<ProjetDTO> listProjet();
    ProjetDTO getProjet(int id) throws ProjectNotFoundException;
}
