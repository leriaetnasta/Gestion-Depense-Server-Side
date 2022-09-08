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


    @ManyToMany(cascade =  CascadeType.ALL,mappedBy = "projet",fetch = FetchType.EAGER)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)

    private Collection<Employe> listEmploye= new ArrayList<>();

    @OneToMany(cascade =  CascadeType.ALL,mappedBy = "projet",fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Deplacement> listDeplacement =new ArrayList<>();

    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="projet_client",joinColumns=@JoinColumn(name = "projet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="client_id"))
    private Client client;
}
