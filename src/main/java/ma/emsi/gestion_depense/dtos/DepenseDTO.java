package ma.emsi.gestion_depense.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.entities.enums.ModeReglement;
import ma.emsi.gestion_depense.entities.enums.Status;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class DepenseDTO {
    private int id;
    private Status status;

    private double montant;

    private ModeReglement modeReglement;

    private String titre;
    private String pieceJustificative;
    private DeplacementDTO deplacement;

    private String commentaire;



}
