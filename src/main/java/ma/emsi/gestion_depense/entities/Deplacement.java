package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Deplacement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    String adresse;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDepart;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRetour;

    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="deplacement_projet",joinColumns=@JoinColumn(name = "deplacement_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="projet_id"))
    private Projet projet;
    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="deplacement_employe",joinColumns=@JoinColumn(name = "deplacement_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="employe_id"))
    private Employe employe;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "deplacement")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private List<Depense> listdepense= new ArrayList<Depense>();



}
