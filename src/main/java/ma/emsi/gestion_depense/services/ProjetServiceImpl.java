package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Client;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.ClientRepository;
import ma.emsi.gestion_depense.repositories.DeplacementRepository;
import ma.emsi.gestion_depense.repositories.EmployeRepository;
import ma.emsi.gestion_depense.repositories.ProjetRepository;
import ma.emsi.gestion_depense.services.interfaces.ClientService;
import ma.emsi.gestion_depense.services.interfaces.DeplacementService;
import ma.emsi.gestion_depense.services.interfaces.EmployeService;
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
    DeplacementService deplacementService;
    EmployeService employeService;
    ClientService clientService;
    GestionDepenseMapper gdp;
    EmployeRepository employeRepository;
    ClientRepository clientRepository;
    DeplacementRepository deplacementRepository;
    ProjetRepository projetRepository;
    @Override
    public ProjetDTO saveProjet(ProjetDTO projetDTO, int idD, int idC, int idE) throws EmployeNotFoundException, DeplacementNotFoundException,ClientNotFoundException {
        log.info("Ajout d'un projet");
        Projet projet=gdp.fromProjetDTO(projetDTO);



        Employe employe=employeRepository.findById(idE).orElse(null);
        if(employe==null) throw new EmployeNotFoundException("Employe not found");

        Client client=clientRepository.findById(idC).orElse(null);
        if(client==null) throw new ClientNotFoundException("Client not Found");
        Deplacement deplacement=deplacementRepository.findById(idD).orElse(null);
        if(deplacement==null) throw new DeplacementNotFoundException("deplacement not found");

        System.out.println(deplacement);
        System.out.println(client);
        System.out.println(employe);

        deplacement.setEmploye(employe);
        employe.getListDeplacement().add(deplacement);
        employe.getProjet().add(projet);

        projet.getListEmploye().add(employe);
        projet.getListDeplacement().add(deplacement);
        projet.setClient(client);
        deplacement.setProjet(projet);
        employe.getProjet().add(projet);
        client.getListProjet().add(projet);
        employeRepository.save(employe);
        clientRepository.save(client);
        deplacementRepository.save(deplacement);
        Projet projet1= projetRepository.save(projet);
        return gdp.fromProjet(projet1);
    }

    @Override
    public void deleteprojet(int projetId) {
        log.info("Suppression du projet N°"+projetId);
        projetRepository.deleteById(projetId);
    }
    @Override
    public  List<ProjetDTO> chercherProjet(String keyword){
        List<Projet> list= projetRepository.searchProjet(keyword);
        List<ProjetDTO> list1= list.stream().map(projet -> gdp.fromProjet(projet)).collect(Collectors.toList());
        return list1;
    }
    @Override
    public ProjetDTO updateProjet(ProjetDTO projetDTO, int idD, int idC, int idE) throws EmployeNotFoundException, DeplacementNotFoundException, ClientNotFoundException {
        log.info("edit projet");

        Employe employe= employeService.getEmploye(idE);
        Deplacement deplacement= gdp.fromDeplacementDTO(deplacementService.getDeplacement(idD));
        Client client=gdp.fromClientDTO(clientService.getClient(idC));
        Projet projet=gdp.fromProjetDTO(projetDTO);
        projet.getListEmploye().add(employe);
        projet.getListDeplacement().add(deplacement);
        projet.setClient(client);
        deplacement.setProjet(projet);
        employe.getProjet().add(projet);
        client.getListProjet().add(projet);
        employeRepository.save(employe);
        clientRepository.save(client);
        deplacementRepository.save(deplacement);
        Projet projet1= projetRepository.save(projet);
        return gdp.fromProjet(projet1);
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

    @Override // I give it a client id and it gives me list of projects of said client
    public List<ProjetDTO> projetOfClientId(int clientId) throws ClientNotFoundException {
        Client client=clientRepository.findById(clientId).orElse(null);
        if(client==null) throw new ClientNotFoundException("Client not Found");
        List<Projet> projets = projetRepository.findByClientId(clientId);
        return projets.stream().map(p->gdp.fromProjet(p)).collect(Collectors.toList());
    }
   

}
