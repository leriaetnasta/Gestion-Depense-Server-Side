package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.EmployeNotFoundException;
import ma.emsi.gestion_depense.Exceptions.MotifNotFoundException;
import ma.emsi.gestion_depense.dtos.EmployeDTO;
import ma.emsi.gestion_depense.dtos.MotifDTO;
import ma.emsi.gestion_depense.services.interfaces.MotifService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class MotifController {
    MotifService motifService;
    @GetMapping("/motifs")
    public List<MotifDTO> motifs(){
        return motifService.listMotif();
    }
    @GetMapping("/motifs/{id}")
    public MotifDTO getMotif(@PathVariable(name="id") int id) throws MotifNotFoundException {
        return motifService.getMotif(id);
    }
    @PostMapping("/motifs")
    public MotifDTO saveMotif(@RequestBody MotifDTO motifDTO) throws MotifNotFoundException {
        return motifService.saveMotif(motifDTO);
    }
    @PutMapping("/motifs/{id}")
    public MotifDTO updateMotif(@PathVariable int id,@RequestBody MotifDTO motifDTO){
        motifDTO.setId(id);
        return motifService.updateMotif(motifDTO);
    }

    @DeleteMapping("/motifs/{id}")
    public void deleteMotif(@PathVariable int id){
        motifService.deleteMotif(id);
    }

}
