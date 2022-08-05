package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.entities.Client;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.ClientRepository;
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






}
