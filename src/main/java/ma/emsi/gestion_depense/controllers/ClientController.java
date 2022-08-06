package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.services.interfaces.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class ClientController {
    private ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDTO> clients(){
        return clientService.listClient();
        
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable(name="id") int id) throws ClientNotFoundException {
        return clientService.getClient(id);
    }

    @PostMapping("/clients")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) throws ClientNotFoundException {
        return clientService.saveClient(clientDTO);
    }

    @PutMapping("/clients/{id}")
    public ClientDTO updateClient(@PathVariable int id,@RequestBody ClientDTO clientDTO){
        clientDTO.setId(id);
        return clientService.updateClient(clientDTO);
    }


    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable int id){
        clientService.deleteClient(id);
    }



}
