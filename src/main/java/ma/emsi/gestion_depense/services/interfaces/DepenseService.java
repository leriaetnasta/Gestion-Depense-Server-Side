package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.entities.Depense;
import ma.emsi.gestion_depense.entities.enums.ModeReglement;

import java.util.List;

public interface DepenseService {

    DepenseDTO saveDepense(DepenseDTO depenseDTO) throws DepenseNotFoundException;

    void deleteDepense(int depenseId);
    Depense editDepense(Depense depense);


    Depense Accepter(int id) throws DepenseNotFoundException;

    Depense Rejeter(int id) throws DepenseNotFoundException;

    List<DepenseDTO> listDepense();

    Depense getDepense(int depenseId) throws DepenseNotFoundException;
}
