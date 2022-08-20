package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.services.interfaces.DeplacementService;
import ma.emsi.gestion_depense.services.interfaces.EmployeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class EmployeController {
    EmployeService employeService;
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

    @DeleteMapping("/employes/{id}")
    public void deleteEmploye(@PathVariable int id){
        employeService.deleteEmploye(id);
    }
}
