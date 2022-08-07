package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.DeplacementRepository;
import ma.emsi.gestion_depense.repositories.EmployeRepository;
import ma.emsi.gestion_depense.repositories.ProjetRepository;
import ma.emsi.gestion_depense.services.interfaces.DeplacementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // les operations sont transactionel
@AllArgsConstructor
//journalisation log4j: pour l'utiliser il ya des api comme slf4j; c'est un framework qui permet la journalisation
@Slf4j
public class DeplacementServiceImpl implements DeplacementService {
    DeplacementRepository deplacementRepository;
    EmployeRepository employeRepository;
    ProjetRepository projetRepository;
    GestionDepenseMapper gdp;

    @Override
    public DeplacementDTO saveDeplacement(DeplacementDTO deplacementDTO) throws DeplacementNotFoundException{
        log.info("Ajout d'un deplacement");
        Deplacement deplacement= gdp.fromDeplacementDTO(deplacementDTO);
        Deplacement deplacement1= deplacementRepository.save(deplacement);
        return gdp.fromDeplacement(deplacement1);
    }

    @Override
    public void deleteDeplacement(int deplacementId) {
        log.info("Suppression du deplacement N°"+deplacementId);

        deplacementRepository.deleteById(deplacementId);
    }

    @Override
    public DeplacementDTO updateDeplacement(DeplacementDTO deplacementDTO) {
        log.info("edit deplacement");
        Deplacement deplacement=gdp.fromDeplacementDTO(deplacementDTO);
        Deplacement deplacement1=deplacementRepository.save(deplacement);
        return  gdp.fromDeplacement(deplacement1);
    }
    @Override
    public List<DeplacementDTO> listDeplacement() {
        List<Deplacement> list= deplacementRepository.findAll();
        return list.stream().map(deplacement -> gdp.fromDeplacement(deplacement)).collect(Collectors.toList());
    }

    @Override
    public DeplacementDTO getDeplacement(int deplacementId) throws DeplacementNotFoundException {
        Deplacement deplacement=deplacementRepository.findById(deplacementId).orElseThrow(()-> new DeplacementNotFoundException("Deplacement Introuvable"));
        return gdp.fromDeplacement(deplacement);
    }
}
