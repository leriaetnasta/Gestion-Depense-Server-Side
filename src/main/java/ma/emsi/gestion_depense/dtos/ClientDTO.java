package ma.emsi.gestion_depense.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.Projet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientDTO {
    private int id;

    String nom;

    private List<ProjetDTO> listProjet=new ArrayList<ProjetDTO>();

}
