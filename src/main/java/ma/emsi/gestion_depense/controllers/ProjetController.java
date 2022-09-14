package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.services.interfaces.ProjetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class ProjetController {
    private ProjetService projetService;
    @GetMapping("/projets")
    public List<ProjetDTO> projets(){
        return projetService.listProjet();

    }
    @GetMapping("/projets/{id}")
    public ProjetDTO getProjet(@PathVariable(name="id") int id) throws ProjectNotFoundException {
        return projetService.getProjet(id);
    }

    @PostMapping("/projets")
    public ProjetDTO saveProjet(@RequestBody ProjetDTO projetDTO) throws ProjectNotFoundException {
        return projetService.saveProjet(projetDTO);
    }

    @PutMapping("/projets/{id}")
    public ProjetDTO updateProjet(@PathVariable int id,@RequestBody ProjetDTO projetDTO){
        projetDTO.setId(id);
        return projetService.updateProjet(projetDTO);
    }
    @DeleteMapping("/projets/{id}")
    public void deleteProjet(@PathVariable int id){
        projetService.deleteprojet(id);
    }

    @GetMapping("/projets/{id}/clients") // find the projects that belong to client of id (passed in param)
    public List<ProjetDTO> getDepenseDeProjet(@PathVariable int id) throws ClientNotFoundException {
        return projetService.projetOfClientId(id);
    }
    @GetMapping("/projets/search")
    public List<ProjetDTO> ChercherProjet(@RequestParam(name="keyword",defaultValue = "") String keyword){
        return projetService.chercherProjet("%"+ keyword+ "%");

    }
}
