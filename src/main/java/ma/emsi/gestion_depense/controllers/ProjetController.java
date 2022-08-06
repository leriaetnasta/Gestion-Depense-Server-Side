package ma.emsi.gestion_depense.controllers;

import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.services.interfaces.ProjetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
