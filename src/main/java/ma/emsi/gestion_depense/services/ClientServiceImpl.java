package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Client;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.ClientRepository;
import ma.emsi.gestion_depense.repositories.ProjetRepository;
import ma.emsi.gestion_depense.services.interfaces.ClientService;
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
    public ClientDTO saveClient(ClientDTO clientDTO) throws ClientNotFoundException {
        log.info("Ajout d'un client");

        Client client= gdp.fromClientDTO(clientDTO);
        Client client1=clientRepository.save(client);
        return gdp.fromClient(client1);
    }
    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        log.info("edit client");
        Client client=gdp.fromClientDTO(clientDTO);
        Client client1=clientRepository.save(client);
        return  gdp.fromClient(client1);
    }
    @Override
    public void deleteClient(int id){
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



}
