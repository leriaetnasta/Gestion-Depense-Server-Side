package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.MotifNotFoundException;
import ma.emsi.gestion_depense.dtos.MotifDTO;

import java.util.List;

public interface MotifService {
    List<MotifDTO> listMotif();

    MotifDTO saveMotif(MotifDTO motifDTO);

    MotifDTO updateMotif(MotifDTO motifDTO);

    void deleteMotif(int motifId);

    MotifDTO getMotif(int motifDTO) throws MotifNotFoundException;
}
