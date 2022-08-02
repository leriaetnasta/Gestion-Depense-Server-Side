package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.services.interfaces.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Slf4j
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


}
