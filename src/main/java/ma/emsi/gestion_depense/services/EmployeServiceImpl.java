package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.entities.enums.Departement;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
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
    GestionDepenseMapper gdp;

    @Override
    public Employe saveEmploye(String nom, String prenom, String phone, String Matricule, Departement departement, String email, List<Projet> listProjet) throws ProjectNotFoundException {
        log.info("Ajout d'un employé");

        if(listProjet==null)
            throw new ProjectNotFoundException("Projet Introuvable");
        Employe employe= new Employe();
        employe.setNom(nom);
        employe.setPrenom(prenom);
        employe.setMatricule(Matricule);
        employe.setEmail(email);
        employe.setPhoneNumber(phone);
        employe.setId((int)(Math.random()*666));
        employe.setDepartement(departement);
        employe.setProjet(listProjet);
        return employeRepository.save(employe);
    }
    @Override
    public Employe saveEmploye2(Employe employe) throws ProjectNotFoundException {
        log.info("Ajout d'un employé");

        return employeRepository.save(employe);
    }

    @Override
    public Employe editEmploye(Employe employe) {
        return null;
    }

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

   /* @Override
    public Employe getEmploye(int employeId) throws EmployeNotFoundException {
        Employe employe=employeRepository.findById(employeId).orElse(null);
        if (employe == null)
            throw new EmployeNotFoundException("Employé Introuvable");

        return employeRepository.findById(employeId).orElseThrow(()-> new EmployeNotFoundException("Employé Introuvable"));

    }*/
    @Override
    public Employe getEmploye(int employeId) throws EmployeNotFoundException {
         return employeRepository.findById(employeId).orElseThrow(()-> new EmployeNotFoundException("Employé Introuvable"));
    }

    @Override
    public Employe chercherEmploye(Employe employe) {
        return null;
    }
}
