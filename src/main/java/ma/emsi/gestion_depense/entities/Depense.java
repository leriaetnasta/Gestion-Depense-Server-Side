package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import ma.emsi.gestion_depense.entities.enums.ModeReglement;
import ma.emsi.gestion_depense.entities.enums.Status;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="depense_deplacement",joinColumns=@JoinColumn(name = "depende_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="deplacement_id"))
    @ToString.Exclude
    private Deplacement deplacement;

    private String titre;
    @Column(nullable = true)
    private String pieceJustificative;
    private double montant;

    private String commentaire;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
