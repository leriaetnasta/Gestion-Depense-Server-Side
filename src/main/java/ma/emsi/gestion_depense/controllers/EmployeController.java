package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.repositories.EmployeRepository;
import ma.emsi.gestion_depense.services.interfaces.DeplacementService;
import ma.emsi.gestion_depense.services.interfaces.EmployeService;
import org.springframework.web.bind.annotation.*;
import ma.emsi.gestion_depense.dtos.EmployeProjetDTO;


import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class EmployeController {
    EmployeService employeService;
    EmployeRepository employeRepository;
    @GetMapping("/employes")
    public List<EmployeDTO> employes(){
        return employeService.listEmploye();
    }
    @GetMapping("/employes/{id}")
    public EmployeDTO getEmploye(@PathVariable(name="id") int id) throws EmployeNotFoundException {
        return employeService.getEmploye(id);
    }


    @PostMapping("/employes")
    public EmployeDTO saveEmploye(@RequestBody EmployeDTO employeDTO) throws EmployeNotFoundException {
        return employeService.saveEmploye(employeDTO);
    }
    @PutMapping("/employes/{id}")
    public EmployeDTO updateEmploye(@PathVariable int id,@RequestBody EmployeDTO employeDTO){
        employeDTO.setId(id);
        return employeService.updateEmploye(employeDTO);
    }

    @PutMapping("/employes/{id}/projets")
    public EmployeDTO addProjetEmploye(@PathVariable int id, @RequestBody EmployeDTO employeDTO, ProjetDTO projetDTO){
        employeDTO.setId(id);
        return employeService.addProjetToEmploye(employeDTO,projetDTO);
    }
    @PutMapping("/employes/{id}/deplacements")
    public EmployeDTO addDeplacementEmploye(@PathVariable int id, @RequestBody EmployeDTO employeDTO, DeplacementDTO deplacementDTO){
        employeDTO.setId(id);
        return employeService.addDeplacementToEmploye(employeDTO,deplacementDTO);
    }

    @DeleteMapping("/employes/{id}")
    public void deleteEmploye(@PathVariable int id){
        employeService.deleteEmploye(id);
    }

    @GetMapping("/employes/getinfo")
    public List<EmployeProjetDTO> printEmployeProjet(){
        return employeRepository.findEmployeAndProject();
    }
}
