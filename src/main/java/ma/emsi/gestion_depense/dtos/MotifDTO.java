package ma.emsi.gestion_depense.dtos;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.Depense;

import javax.persistence.*;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class MotifDTO {
    private int id;

    private String titre;
    private String pieceJustificative;

    private double montant;
    private DepenseDTO depense;

}
