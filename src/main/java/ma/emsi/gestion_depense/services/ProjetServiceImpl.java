package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.repositories.ProjetRepository;
import ma.emsi.gestion_depense.services.interfaces.ProjetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional // les operations sont transactionel
@AllArgsConstructor
//journalisation log4j: pour l'utiliser il ya des api comme slf4j; c'est un framework qui permet la journalisation
@Slf4j
public class ProjetServiceImpl implements ProjetService {
    ProjetRepository projetRepository;
    @Override
    public Projet saveProjet(String titre, Date dateDebut, Date dateFin, double montantTotal) {
        log.info("Ajout d'un projet");
        Projet projet= new Projet();
        projet.setTitre(titre);
        projet.setMontantTotal(montantTotal);
        projet.setDateDebut(dateDebut);
        projet.setDateFin(dateFin);
        projetRepository.save(projet);
        return null;
    }

    @Override
    public void deleteprojet(int projetId) {
        log.info("Suppression du projet N°"+projetId);
        projetRepository.deleteById(projetId);
    }

    @Override
    public Projet editProjet(Projet projet) {
        return null;
    }

    @Override
    public List<Projet> listProjet() {
        return projetRepository.findAll();
    }

    @Override
    public Projet getProjet(int id) throws ProjectNotFoundException {
        return projetRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Projet Introuvable"));
    }


}
