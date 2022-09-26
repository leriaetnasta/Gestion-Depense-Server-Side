package ma.emsi.gestion_depense.controllers;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ma.emsi.gestion_depense.entities.Depense;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import org.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.Exceptions.ProjectNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.services.interfaces.DepenseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
public class DepenseController {
    DepenseService depenseService;
    GestionDepenseMapper gdp;
    @GetMapping("/user/depenses")
    public List<DepenseDTO> depenses(){
        return depenseService.listDepense();
    }
    @GetMapping("/user/depenses/{id}")
    public DepenseDTO getDepense(@PathVariable(name="id") int id) throws DepenseNotFoundException {
        return depenseService.getDepense(id);
    }
    /*@PostMapping(value = "/admin/depenses2")
    public DepenseDTO saveDepense2(@RequestBody DepenseDTO depenseDTO)  {
        return depenseService.saveDepense(depenseDTO);
    }*/

    @PostMapping(value = "/admin/depenses")
    public DepenseDTO saveDepense(@RequestBody String myObject ) throws DepenseNotFoundException, DeplacementNotFoundException {
        JSONObject jsonObject = new JSONObject(myObject);
        JSONObject myobj = jsonObject.getJSONObject("depense");
        int idD= jsonObject.getInt("idD");
        Gson gson= new Gson();
        DepenseDTO obj = gson.fromJson(myobj.toString(),DepenseDTO.class);
        return depenseService.saveDepense(obj, idD);
    }




    @PutMapping("/admin/depenses/{id}")

    public DepenseDTO updateDepense(@PathVariable int id,@RequestBody String myObject) throws DepenseNotFoundException, DeplacementNotFoundException {
        JSONObject jsonObject = new JSONObject(myObject);
        JSONObject myobj = jsonObject.getJSONObject("depense");
        int idDep= jsonObject.getInt("idDep");
        Gson gson= new Gson();
        DepenseDTO obj = gson.fromJson(myobj.toString(),DepenseDTO.class);
        return depenseService.updateDepense(obj,idDep);
    }

    @DeleteMapping("/admin/depenses/{id}")
    public void deleteDepense(@PathVariable int id){
        depenseService.deleteDepense(id);
    }

    @GetMapping(path="/imageRecu/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable(name="id")int id) throws DepenseNotFoundException, IOException {
        return depenseService.uploadImg(id);
    }
   /* public void uploadImage(MultipartFile file) throws IOException {
        Image image= new Image(file.getOriginalFilename(),file.getContentType(),file.getBytes());

    }

    @PostMapping(value = "/admin/depenses",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DepenseDTO saveDepense(@RequestBody DepenseDTO depenseDTO, @RequestPart("imageFile") MultipartFile file) throws DepenseNotFoundException {
        try {
            Image image=uploadImage(file);
            depenseDTO.setPieceJustificative(image);
            return depenseService.saveDepense(depenseDTO);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }*/

    @PutMapping("/admin/depenses/{id}/addDeplacement")

    public void addDeplacementDepense( @PathVariable(name="id") int id, @RequestBody int[]  arr) throws DepenseNotFoundException, DeplacementNotFoundException {

        log.info("ajout deplacement "+arr[1]+" au depense "+arr[0]);
        depenseService.addDeplacementToDepense(id,arr[1]);
    }
}
