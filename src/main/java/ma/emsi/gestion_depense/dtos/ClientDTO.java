package ma.emsi.gestion_depense.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientDTO {
    private int id;

    String nom;
}
