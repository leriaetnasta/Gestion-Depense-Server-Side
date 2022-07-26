package ma.emsi.gestion_depense.security.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true)
    private String roleName;
    private String description;
}
