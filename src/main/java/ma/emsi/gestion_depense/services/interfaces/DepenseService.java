package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.entities.Depense;
import ma.emsi.gestion_depense.entities.enums.ModeReglement;

import java.io.IOException;
import java.util.List;

public interface DepenseService {




    DepenseDTO saveDepense(DepenseDTO depenseDTO, int idD) throws DepenseNotFoundException, DeplacementNotFoundException;


    void deleteDepense(int depenseId);

    //DepenseDTO updateDepense(DepenseDTO depenseDTO) throws DepenseNotFoundException;

    DepenseDTO updateDepense(DepenseDTO depenseDTO, int idDep) throws DepenseNotFoundException, DeplacementNotFoundException;

    DepenseDTO Accepter(int id) throws DepenseNotFoundException;

    DepenseDTO Rejeter(int id) throws DepenseNotFoundException;

    List<DepenseDTO> listDepense();

    DepenseDTO getDepense(int depenseId) throws DepenseNotFoundException;

    byte[] uploadImg(int id) throws DepenseNotFoundException, IOException;

    void addDeplacementToDepense(int id, int i)throws DepenseNotFoundException,DeplacementNotFoundException;
}
