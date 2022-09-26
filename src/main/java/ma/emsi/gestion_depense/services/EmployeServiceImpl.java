package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.DeplacementRepository;
import ma.emsi.gestion_depense.repositories.EmployeRepository;
import ma.emsi.gestion_depense.repositories.ProjetRepository;
import ma.emsi.gestion_depense.services.interfaces.EmployeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // les operations sont transactionel
@AllArgsConstructor
//journalisation log4j: pour l'utiliser il ya des api comme slf4j; c'est un framework qui permet la journalisation
@Slf4j
public class EmployeServiceImpl implements EmployeService {
    EmployeRepository employeRepository;
    ProjetRepository projetRepository;
    DeplacementRepository deplacementRepository;
    GestionDepenseMapper gdp;

    @Override
    public EmployeDTO saveEmploye(EmployeDTO employeDTO, int idP, int idD) throws ProjectNotFoundException, DeplacementNotFoundException {
        log.info("Ajout d'un employé");
        Projet projet=projetRepository.findById(idP).orElse(null);
        if(projet==null) throw new ProjectNotFoundException("projet not found");
        Deplacement deplacement=deplacementRepository.findById(idD).orElse(null);
        if(deplacement==null) throw new DeplacementNotFoundException("deplacement not found");
        Employe employe= gdp.fromEmployeDTO(employeDTO);
        deplacement.setEmploye(employe);
        employe.getListDeplacement().add(deplacement);
        employe.getProjet().add(projet);
        Employe employe1= employeRepository.save(employe);
        projet.getListEmploye().add(employe);
        projetRepository.save(projet);
        deplacementRepository.save(deplacement);
        return gdp.fromEmploye(employe1);
    }
    @Override
    public EmployeDTO addProjetToEmploye(EmployeDTO employeDTO, ProjetDTO projetDTO){
        log.info("affectation d'un projet a un employé");
        Employe employe= gdp.fromEmployeDTO(employeDTO);
        Projet projet=gdp.fromProjetDTO(projetDTO);
        if(employe.getProjet()!=null){
            employe.getProjet().add(projet);
            projet.getListEmploye().add(employe); //pour etre correct dans l'oop
        }
        Employe employe1= employeRepository.save(employe);
        return gdp.fromEmploye(employe1);

    }
    @Override

    public EmployeDTO addDeplacementToEmploye(EmployeDTO employeDTO, DeplacementDTO deplacementDTO){

        log.info("affectation d'un deplacement a un employé");
        Employe employe= gdp.fromEmployeDTO(employeDTO);
        Deplacement deplacement=gdp.fromDeplacementDTO(deplacementDTO);
        if(employe.getListDeplacement()!=null){
            employe.getListDeplacement().add(deplacement);
            deplacement.setEmploye(employe);
        }
        Employe employe1= employeRepository.save(employe);
        return gdp.fromEmploye(employe1);
    }

    @Override
    public EmployeDTO updateEmploye(int id,EmployeDTO employeDTO, int idP, int idD) throws ProjectNotFoundException, DeplacementNotFoundException {
        log.info("Ajout d'un employé");
        Projet projet=projetRepository.findById(idP).orElse(null);
        if(projet==null) throw new ProjectNotFoundException("projet not found");
        Deplacement deplacement=deplacementRepository.findById(idD).orElse(null);
        if(deplacement==null) throw new DeplacementNotFoundException("deplacement not found");
        Employe employe= gdp.fromEmployeDTO(employeDTO);
        deplacement.setEmploye(employe);
        employe.getListDeplacement().add(deplacement);
        employe.getProjet().add(projet);
        Employe employe1= employeRepository.save(employe);
        projet.getListEmploye().add(employe);
        projetRepository.save(projet);
        deplacementRepository.save(deplacement);
        return gdp.fromEmploye(employe1);}

    @Override
    public List<EmployeDTO> listEmploye() {
        List<Employe> list= employeRepository.findAll();
        return list.stream().map(employe -> gdp.fromEmploye(employe)).collect(Collectors.toList());
    }


    @Override
    public void deleteEmploye(int employeId) {
        log.info("Suppression de l'employé N°"+employeId);

        employeRepository.deleteById(employeId);
    }


    @Override
    public Employe getEmploye(int employeId) throws EmployeNotFoundException {
         Employe employe= employeRepository.findById(employeId).orElseThrow(()-> new EmployeNotFoundException("Employé Introuvable"));
         return employe;
    }


    @Override
    public List<EmployeDTO> chercherEmploye(String keyword) {
        List<Employe> employes=employeRepository.rechercheEmploye(keyword);
        List<EmployeDTO> employeDTOS = employes.stream().map(employe -> gdp.fromEmploye(employe)).collect(Collectors.toList());
        return employeDTOS;
    }



}
