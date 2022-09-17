package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.entities.Projet;
import ma.emsi.gestion_depense.services.interfaces.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class ClientController {
    private ClientService clientService;

    @GetMapping("/user/clients")
    public List<ClientDTO> clients(){
        return clientService.listClient();
        
    }

    @GetMapping("/user/clients/search")
    public List<ClientDTO> ChercherClient(@RequestParam(name="keyword",defaultValue = "") String keyword){
        return clientService.chercherClient("%"+ keyword+ "%");

    }

    @GetMapping("/user/clients/{id}")
    public ClientDTO getClient(@PathVariable(name="id") int id) throws ClientNotFoundException {
        return clientService.getClient(id);
    }

    @PostMapping("/admin/clients")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) throws ClientNotFoundException {
        return clientService.saveClient(clientDTO);
    }

    @PutMapping("/admin/clients/update/{id}")
    public ClientDTO updateClient(@PathVariable int id,@RequestBody ClientDTO clientDTO){
        clientDTO.setId(id);
        return clientService.updateClient(clientDTO);
    }


    @DeleteMapping("/admin/clients/{id}")
    public void deleteClient(@PathVariable int id){
        clientService.deleteClient(id);
    }

    @PutMapping("/admin/clients/{id}/addprojets")

    public void addProjetEmploye( @PathVariable(name="id") int id, @RequestBody int[]  arr) throws ClientNotFoundException, ProjectNotFoundException {

        log.info("ajout projet "+arr[1]+" au client "+arr[0]);
        clientService.addProjetToClient(id,arr[1]);
    }
    @GetMapping("/user/clients/{id}/projets")
    @ResponseStatus(HttpStatus.OK) // this method is supposed to print every project that is below the client
    public List<Projet> getProjets(@PathVariable(name="id") int id) throws ClientNotFoundException,ProjectNotFoundException
    {
        return clientService.getprojets(id);
    }

   /* @GetMapping("/user/clients/{id}/clientprojets")
    public List<ProjetDTO> listerClientProjets( @PathVariable int id) throws ProjectNotFoundException, ClientNotFoundException {
        return clientService.getClientProjets(id);

    }*/




}
