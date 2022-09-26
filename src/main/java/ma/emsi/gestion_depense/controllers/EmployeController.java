package ma.emsi.gestion_depense.controllers;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.*;
import ma.emsi.gestion_depense.entities.Employe;
import ma.emsi.gestion_depense.repositories.EmployeRepository;
import ma.emsi.gestion_depense.services.interfaces.DeplacementService;
import ma.emsi.gestion_depense.services.interfaces.EmployeService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
//@CrossOrigin("*")
public class EmployeController {
    EmployeService employeService;
    EmployeRepository employeRepository;
    @GetMapping("/user/employes")
    public List<EmployeDTO> employes(){
        return employeService.listEmploye();
    }
    @GetMapping("/user/employes/{id}")
    public Employe getEmploye(@PathVariable(name="id") int id) throws EmployeNotFoundException {
        return employeService.getEmploye(id);
    }


    @PostMapping("/admin/employes")
    public EmployeDTO saveEmploye(@RequestBody String myObject) throws EmployeNotFoundException, ProjectNotFoundException, DeplacementNotFoundException {
        JSONObject jsonObject = new JSONObject(myObject);
        JSONObject myobj = jsonObject.getJSONObject("employe");
        int idP= jsonObject.getInt("idP");
        int idD= jsonObject.getInt("idD");
        Gson gson= new Gson();
        EmployeDTO obj = gson.fromJson(myobj.toString(),EmployeDTO.class);

        return employeService.saveEmploye(obj,idP, idD);
    }
    @PutMapping("/admin/employes/{id}")
    public EmployeDTO updateEmploye(@PathVariable int id, @RequestBody String myObject) throws EmployeNotFoundException, ProjectNotFoundException, DeplacementNotFoundException {
        JSONObject jsonObject = new JSONObject(myObject);
        JSONObject myobj = jsonObject.getJSONObject("employe");
        int idP= jsonObject.getInt("idP");
        int idD= jsonObject.getInt("idD");
        Gson gson= new Gson();
        EmployeDTO obj = gson.fromJson(myobj.toString(),EmployeDTO.class);
        obj.setId(id);
        return employeService.updateEmploye(id,obj,idP, idD);
    }

    @PutMapping("/admin/employes/{id}/projets")
    public EmployeDTO addProjetEmploye(@PathVariable int id, @RequestBody EmployeDTO employeDTO, ProjetDTO projetDTO){
        employeDTO.setId(id);
        return employeService.addProjetToEmploye(employeDTO,projetDTO);
    }
    @PutMapping("/admin/employes/{id}/deplacements")
    public EmployeDTO addDeplacementEmploye(@PathVariable int id, @RequestBody EmployeDTO employeDTO, DeplacementDTO deplacementDTO){
        employeDTO.setId(id);
        return employeService.addDeplacementToEmploye(employeDTO,deplacementDTO);
    }

    @DeleteMapping("/admin/employes/{id}")
    public void deleteEmploye(@PathVariable int id){
        employeService.deleteEmploye(id);
    }

    @GetMapping("/user/employes/getinfo")
    public List<EmployeProjetDTO> printEmployeProjet(){
        return employeRepository.findEmployeAndProject();
    }
}
