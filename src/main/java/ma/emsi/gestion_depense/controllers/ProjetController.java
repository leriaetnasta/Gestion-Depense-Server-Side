package ma.emsi.gestion_depense.controllers;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.dtos.ProjetDTO;
import ma.emsi.gestion_depense.services.interfaces.ProjetService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@Slf4j
@RestController
//@CrossOrigin("*")
public class ProjetController {
    private ProjetService projetService;
    @GetMapping("/user/projets")
    public List<ProjetDTO> projets(){
        return projetService.listProjet();

    }
    @GetMapping("/user/projets/{id}")
    public ProjetDTO getProjet(@PathVariable(name="id") int id) throws ProjectNotFoundException {

        return projetService.getProjet(id);
    }

    @PostMapping("/admin/projets")
    public ProjetDTO saveProjet(@RequestBody String myObject) throws ProjectNotFoundException, ClientNotFoundException, EmployeNotFoundException, DeplacementNotFoundException {
        JSONObject jsonObject = new JSONObject(myObject);
        JSONObject myobj = jsonObject.getJSONObject("projet");
        int idD= jsonObject.getInt("idD");
        int idC= jsonObject.getInt("idC");
        int idE= jsonObject.getInt("idE");

        Gson gson= new Gson();
        ProjetDTO obj = gson.fromJson(myobj.toString(),ProjetDTO.class);
        System.out.println(obj);
        System.out.println(idC);
        System.out.println(idE);
        System.out.println(idD);

        return projetService.saveProjet(obj,idD,idC,idE);
    }

    @PutMapping("/admin/projets/{id}")
    public ProjetDTO updateProjet(@PathVariable(name="id") int id,@RequestBody String myObject) throws ProjectNotFoundException, ClientNotFoundException, EmployeNotFoundException, DeplacementNotFoundException {
        JSONObject jsonObject = new JSONObject(myObject);
        JSONObject myobj = jsonObject.getJSONObject("projet");
        int idD= jsonObject.getInt("idD");
        int idC= jsonObject.getInt("idC");
        int idE= jsonObject.getInt("idE");
        Gson gson= new Gson();
        ProjetDTO obj = gson.fromJson(myobj.toString(),ProjetDTO.class);

        return projetService.updateProjet(obj,idD,idC,idE);
    }
    @DeleteMapping("/admin/projets/{id}")
    public void deleteProjet(@PathVariable int id){
        projetService.deleteprojet(id);
    }

    @GetMapping("/user/projets/{id}/clients") // find the projects that belong to client of id (passed in param)
    public List<ProjetDTO> getDepenseDeProjet(@PathVariable int id) throws ClientNotFoundException {
        return projetService.projetOfClientId(id);
    }
    @GetMapping("/user/projets/search")
    public List<ProjetDTO> ChercherProjet(@RequestParam(name="keyword",defaultValue = "") String keyword){
        return projetService.chercherProjet("%"+ keyword+ "%");

    }
}
