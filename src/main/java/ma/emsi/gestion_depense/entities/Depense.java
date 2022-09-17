package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.enums.ModeReglement;
import ma.emsi.gestion_depense.entities.enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor

public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;


    @NotNull
    @Enumerated(EnumType.STRING)
    private ModeReglement modeReglement;

    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="depense_deplacement",joinColumns=@JoinColumn(name = "depende_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="deplacement_id"))
    private Deplacement deplacement;

    private String titre;
    @Column(nullable = true)
    private String pieceJustificative;
    private double montant;

    private String commentaire;


}
