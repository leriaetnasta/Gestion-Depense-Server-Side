package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Motif {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String titre;
    @Column(nullable = true, length = 64)
    private String pieceJustificative;

    private double montant;
    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="motif_depense",joinColumns=@JoinColumn(name = "motif_id" ,referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="depense_id"))
    private Depense depense;

}
