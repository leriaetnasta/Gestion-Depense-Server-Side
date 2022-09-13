package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.services.interfaces.DeplacementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class DeplacementController {
    DeplacementService deplacementService;
    @GetMapping("/deplacements")
    public List<DeplacementDTO> deplacements(){
        return deplacementService.listDeplacement();
    }
    @GetMapping("/deplacements/{id}")
    public DeplacementDTO getDeplacement(@PathVariable(name="id") int id) throws DeplacementNotFoundException {
        return deplacementService.getDeplacement(id);
    }
    @PostMapping("/deplacements")
    public DeplacementDTO saveDeplacement(@RequestBody DeplacementDTO deplacementDTO) throws DeplacementNotFoundException {
        return deplacementService.saveDeplacement(deplacementDTO);
    }
    @PutMapping("/deplacements/{id}")

    public DeplacementDTO updateDeplacement(@PathVariable int id,@RequestBody DeplacementDTO deplacementDTO){
        deplacementDTO.setId(id);
        return deplacementService.updateDeplacement(deplacementDTO);
    }

    @DeleteMapping("/deplacements/{id}")
    public void deleteDeplacement(@PathVariable int id){
        deplacementService.deleteDeplacement(id);
    }





}
