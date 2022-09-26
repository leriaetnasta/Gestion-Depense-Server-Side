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
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String titre;
    private double MontantTotal;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;


    @ManyToMany(mappedBy = "projet",fetch = FetchType.EAGER)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)

    private List<Employe> listEmploye= new ArrayList<Employe>();

    @OneToMany(mappedBy = "projet",fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private List<Deplacement> listDeplacement =new ArrayList<Deplacement>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="projet_client",joinColumns=@JoinColumn(name = "projet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="client_id"))
    @ToString.Exclude
    private Client client;


}
