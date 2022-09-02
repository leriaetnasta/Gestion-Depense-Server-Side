package ma.emsi.gestion_depense.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.entities.enums.ModeReglement;
import ma.emsi.gestion_depense.entities.enums.Status;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class DepenseDTO {
    private int id;
    private Status status;

    private double montant;

    private ModeReglement modeReglement;

    private DeplacementDTO deplacement;

    private String commentaire;


}
