package ma.emsi.gestion_depense.controllers;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.dtos.DeplacementDTO;
import ma.emsi.gestion_depense.services.interfaces.DeplacementService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
//@CrossOrigin("*")
public class DeplacementController {
    DeplacementService deplacementService;
    @GetMapping("/user/deplacements")
    public List<DeplacementDTO> deplacements(){
        return deplacementService.listDeplacement();
    }
    @GetMapping("/user/deplacements/{id}")
    public DeplacementDTO getDeplacement(@PathVariable(name="id") int id) throws DeplacementNotFoundException {
        return deplacementService.getDeplacement(id);
    }
    @PostMapping("/admin/deplacements")
    public DeplacementDTO saveDeplacement(@RequestBody String myObject) throws DeplacementNotFoundException, ProjectNotFoundException, EmployeNotFoundException, DepenseNotFoundException {
        JSONObject jsonObject= new JSONObject(myObject);
        JSONObject myobj=jsonObject.getJSONObject("deplacement");
        int idP=jsonObject.getInt("idP");
        int idE=jsonObject.getInt("idE");
        int idD=jsonObject.getInt("idD");
        Gson gson=new Gson();
        DeplacementDTO deplacementDTO=gson.fromJson(myobj.toString(),DeplacementDTO.class);
        return deplacementService.saveDeplacement(deplacementDTO,idP,idE,idD);
    }
    @PutMapping("/admin/deplacements/{id}")

    public DeplacementDTO updateDeplacement(@PathVariable int id, @RequestBody String myObject) throws ProjectNotFoundException, EmployeNotFoundException, DepenseNotFoundException {
        JSONObject jsonObject= new JSONObject(myObject);
        JSONObject myobj=jsonObject.getJSONObject("deplacement");
        int idP=jsonObject.getInt("idP");
        int idE=jsonObject.getInt("idE");
        int idD=jsonObject.getInt("idD");
        Gson gson=new Gson();
        DeplacementDTO deplacementDTO=gson.fromJson(myobj.toString(),DeplacementDTO.class);
        return deplacementService.updateDeplacement(deplacementDTO,  idP,  idE,  idD);
    }

    @DeleteMapping("/admin/deplacements/{id}")
    public void deleteDeplacement(@PathVariable int id){
        deplacementService.deleteDeplacement(id);
    }





}
