package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.ProjetRepository;
import ma.emsi.gestion_depense.services.interfaces.ProjetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // les operations sont transactionel
@AllArgsConstructor
//journalisation log4j: pour l'utiliser il ya des api comme slf4j; c'est un framework qui permet la journalisation
@Slf4j
public class ProjetServiceImpl implements ProjetService {
    ProjetRepository projetRepository;
    GestionDepenseMapper gdp;

    @Override
    public ProjetDTO saveProjet(ProjetDTO projetDTO) throws ProjectNotFoundException {
        log.info("Ajout d'un projet");
        Projet projet= gdp.fromProjetDTO(projetDTO);
        Projet projet1= projetRepository.save(projet);
        return gdp.fromProjet(projet1);
    }

    @Override
    public void deleteprojet(int projetId) {
        log.info("Suppression du projet N°"+projetId);
        projetRepository.deleteById(projetId);
    }

    @Override
    public ProjetDTO updateProjet(ProjetDTO projetDTO) {
        log.info("edit projet");
        Projet projet=gdp.fromProjetDTO(projetDTO);
        Projet projet1=projetRepository.save(projet);
        return  gdp.fromProjet(projet1);
    }
    @Override
    public List<ProjetDTO> listProjet() {
        List<Projet> list= projetRepository.findAll();
        return list.stream().map(projet -> gdp.fromProjet(projet)).collect(Collectors.toList());
    }

    @Override
    public ProjetDTO getProjet(int id) throws ProjectNotFoundException {
        Projet projet=projetRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Projet Introuvable"));
        return gdp.fromProjet(projet);
    }


}
