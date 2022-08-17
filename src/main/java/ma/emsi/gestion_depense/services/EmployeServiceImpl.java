package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.entities.Employe;
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
    public Employe saveEmploye(EmployeDTO employeDTO) {
        log.info("Ajout d'un employé");
        Employe employe= gdp.fromEmployeDTO(employeDTO);
        Employe employe1= employeRepository.save(employe);
        return employe1;
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


    @Override
    public Employe getEmploye(int employeId) throws EmployeNotFoundException {
         return employeRepository.findById(employeId).orElseThrow(()-> new EmployeNotFoundException("Employé Introuvable"));
    }

    @Override
    public Employe chercherEmploye(Employe employe) {
        return null;
    }
}
