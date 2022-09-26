package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import ma.emsi.gestion_depense.entities.enums.Departement;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Departement departement;

    @Size(max = 15)
    private String phoneNumber;

    @NotNull
    private String matricule;

    @NotNull
    @ManyToMany(targetEntity = Projet.class,fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="employe_projet",joinColumns=@JoinColumn(name = "employe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="projet_id"))
    @ToString.Exclude
    private List<Projet> projet= new ArrayList<Projet>();

    @NotNull
    @OneToMany(mappedBy = "employe",fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private List<Deplacement> listDeplacement= new ArrayList<Deplacement>();


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
