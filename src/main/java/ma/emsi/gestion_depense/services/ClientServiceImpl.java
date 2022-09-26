package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.entities.Client;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.ClientRepository;
import ma.emsi.gestion_depense.repositories.ProjetRepository;
import ma.emsi.gestion_depense.services.interfaces.ClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional // les operations sont transactionel
@AllArgsConstructor
//journalisation log4j: pour l'utiliser il ya des api comme slf4j; c'est un framework qui permet la journalisation
@Slf4j
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    ProjetRepository projetRepository;
    GestionDepenseMapper gdp;
     @Override
    public List<ClientDTO> listClient() {
        List<Client> list= clientRepository.findAll();
        return list.stream().map(client -> gdp.fromClient(client)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClient(int id) throws ClientNotFoundException {
         Client client=clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException("Client Introuvable"));
         return gdp.fromClient(client);
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO, int idP) throws ProjectNotFoundException {
        log.info("Ajout d'un client");
        //step 1: we cast the objectDTO into an object
        Client client= gdp.fromClientDTO(clientDTO);
        //step 2: search if the related object exists in its table, we take the id of the object and look for it
        //if object not found we throw an exception
        Projet projet=projetRepository.findById(idP).orElse(null);
        if(projet==null) throw new ProjectNotFoundException("projet not found");
        //step 3: after we'e  established that the object exists now we add the object into the list
        client.getListProjet().add(projet);
        //step 4: to respect oop we add the object in the related object's table
        projet.setClient(client);
        //step 5: we save the related object
        projetRepository.save(projet);
        //step 6: we save our object and return a DTO
        Client client1=clientRepository.save(client);
        return gdp.fromClient(client1);
    }
    @Override
    public ClientDTO updateClient(ClientDTO clientDTO, int idP) throws ProjectNotFoundException {
        log.info("edit client");
        //step 1: we cast the objectDTO into an object
        Client client= gdp.fromClientDTO(clientDTO);
        //step 2: search if the related object exists in its table, we take the id of the object and look for it
        //if object not found we throw an exception
        Projet projet=projetRepository.findById(idP).orElse(null);
        if(projet==null) throw new ProjectNotFoundException("projet not found");
        //step 3: after we'e  established that the object exists now we add the object into the list
        client.getListProjet().add(projet);
        //step 4: to respect oop we add the object in the related object's table
        projet.setClient(client);
        //step 5: we save the related object
        projetRepository.save(projet);
        //step 6: we save our object and return a DTO
        Client client1=clientRepository.save(client);
        return gdp.fromClient(client1);
    }
    @Override
    public void deleteClient(int id) throws ClientNotFoundException {
        Client client =clientRepository.findById(id).orElse(null);
        if(client==null) throw new ClientNotFoundException("client not found");
        clientRepository.deleteById(id);
    }

    @Override
    public  List<ClientDTO> chercherClient(String keyword){
        List<Client> list= clientRepository.searchClient(keyword);
        List<ClientDTO> list1= list.stream().map(client -> gdp.fromClient(client)).collect(Collectors.toList());
        return list1;
    }
    @Override //looks up a project via it's id then adds it to list of projects that belong to client whom id was passed
    public void addProjetToClient(int clientId, int projectId) throws ClientNotFoundException, ProjectNotFoundException {
        log.info("Operation: Ajout d'un projet a un client");

        Client client= clientRepository.findById(clientId).orElse(null);
        if(client==null) throw new ClientNotFoundException("client not found");
        Projet projet=projetRepository.findById(projectId).orElse(null);
        if(projet==null) throw new ProjectNotFoundException("projet not found");
        client.getListProjet().add(projet);
        projet.setClient(client);
        clientRepository.save(client);
        projetRepository.save(projet);
    }

    @Override
    public List<Projet> getprojets(int id) throws ClientNotFoundException, ProjectNotFoundException {

        Client client= clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Aucun client avec l'id "+id+" trouvé"));
        if(client.getListProjet().isEmpty()) throw new ProjectNotFoundException("Ce client n'a aucun projet.");
        return client.getListProjet();
    }

   /* @Override
    public List<ProjetDTO> getClientProjets(int id) throws ClientNotFoundException, ProjectNotFoundException {

        Client client= clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Aucun client avec l'id "+id+" trouvé"));
        if(client.getListProjet().isEmpty()) throw new ProjectNotFoundException("Ce client n'a aucun projet.");
        List<ProjetDTO> projetDTOS= client.getListProjet().stream().map(p -> gdp.fromProjet(p)).collect(Collectors.toList());
        return projetDTOS;
    }*/


    @Override
    public void removeProjetFromClient(int idC, int idP ) throws ClientNotFoundException, ProjectNotFoundException {
        Client client =clientRepository.findById(idC).orElse(null);
        if(client==null) throw new ClientNotFoundException("client not found");
        Projet projet=projetRepository.findById(idP).orElse(null);
        if(projet==null) throw new ProjectNotFoundException("projet not found");
        client.getListProjet().remove(projet);
        clientRepository.save(client);
    }

}
