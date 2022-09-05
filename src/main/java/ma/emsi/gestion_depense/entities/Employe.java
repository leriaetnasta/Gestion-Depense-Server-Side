package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.gestion_depense.entities.enums.Departement;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
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
    @ManyToMany(cascade =  CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private List<Projet> projet= new ArrayList<>();

    @NotNull
    @OneToMany(cascade =  CascadeType.ALL,mappedBy = "employe",fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private List<Deplacement> listDeplacement= new ArrayList<>();


}
