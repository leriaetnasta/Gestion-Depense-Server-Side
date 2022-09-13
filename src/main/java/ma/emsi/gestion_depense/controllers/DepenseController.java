package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.services.interfaces.DepenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class DepenseController {
    DepenseService depenseService;
    @GetMapping("/depenses")
    public List<DepenseDTO> depenses(){
        return depenseService.listDepense();

    }
    @GetMapping("/depenses/{id}")
    public DepenseDTO getDepense(@PathVariable(name="id") int id) throws DepenseNotFoundException {
        return depenseService.getDepense(id);
    }
    @PostMapping("/depenses")
    public DepenseDTO saveDepense(@RequestBody DepenseDTO depenseDTO) throws DepenseNotFoundException {
        return depenseService.saveDepense(depenseDTO);
    }
    @PutMapping("/depenses/{id}")

    public DepenseDTO updateDepense(@PathVariable int id,@RequestBody DepenseDTO depenseDTO) throws DepenseNotFoundException {
        depenseDTO.setId(id);
        return depenseService.updateDepense(depenseDTO);
    }

    @DeleteMapping("/depenses/{id}")
    public void deleteDepense(@PathVariable int id){
        depenseService.deleteDepense(id);
    }

}
