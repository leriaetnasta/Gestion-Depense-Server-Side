package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="deplacement_projet",joinColumns=@JoinColumn(name = "deplacement_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="projet_id"))
    @ToString.Exclude
    private Projet projet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="deplacement_employe",joinColumns=@JoinColumn(name = "deplacement_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="employe_id"))
    @ToString.Exclude
    private Employe employe;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deplacement")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private List<Depense> listdepense= new ArrayList<Depense>();



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
