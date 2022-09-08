package ma.emsi.gestion_depense.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    String nom;

    @OneToMany(cascade =  CascadeType.ALL,mappedBy = "client",fetch = FetchType.LAZY)
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Projet> listProjet= new ArrayList<>();
}
