package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.enums.ModeReglement;
import ma.emsi.gestion_depense.entities.enums.Status;

import javax.persistence.*;
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

    private double montant;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ModeReglement modeReglement;

    @ManyToOne
    @JoinColumn(name = "deplacement_id" ,referencedColumnName = "id")
    private Deplacement deplacement;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "depense")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private List<Motif> listMotif;

    private String commentaire;


}
