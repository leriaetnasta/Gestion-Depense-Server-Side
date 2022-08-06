package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.entities.Depense;
import ma.emsi.gestion_depense.entities.enums.Status;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.DeplacementRepository;
import ma.emsi.gestion_depense.repositories.DepenseRepository;
import ma.emsi.gestion_depense.services.interfaces.DepenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // les operations sont transactionel
@AllArgsConstructor
//journalisation log4j: pour l'utiliser il ya des api comme slf4j; c'est un framework qui permet la journalisation
@Slf4j
public class DepenseServiceImpl implements DepenseService {
    DeplacementRepository deplacementRepository;
    DepenseRepository depenseRepository;
    GestionDepenseMapper gdp;

    @Override
    public DepenseDTO saveDepense(DepenseDTO depenseDTO) throws DepenseNotFoundException {
        log.info("ajout d'une depense");
        Depense depense=gdp.fromDepenseDTO(depenseDTO);
        Depense depense1= depenseRepository.save(depense);
        return gdp.fromDepense(depense1);
    }

    @Override
    public void deleteDepense(int depenseid) {
        log.info("Suppression du depense N°"+depenseid);

        depenseRepository.deleteById(depenseid);
    }

    @Override
    public Depense editDepense(Depense depense) {
        return null;
    }

    @Override
    public Depense Accepter(int id) throws DepenseNotFoundException {
        Depense depense= depenseRepository.findById(id).orElseThrow(()-> new DepenseNotFoundException("Deplacement Introuvable"));
        depense.setStatus(Status.ACCEPTE);
        return depense;
    }
    @Override
    public Depense Rejeter(int id) throws DepenseNotFoundException {
        Depense depense= depenseRepository.findById(id).orElseThrow(()-> new DepenseNotFoundException("Deplacement Introuvable"));
        depense.setStatus(Status.REJETE);
        return depense;
    }

    @Override
    public List<DepenseDTO> listDepense() {
        List<Depense> list= depenseRepository.findAll();
        return list.stream().map(depense -> gdp.fromDepense(depense)).collect(Collectors.toList());
    }

    @Override
    public Depense getDepense(int depenseId) throws DepenseNotFoundException {
        return depenseRepository.findById(depenseId).orElseThrow(()-> new DepenseNotFoundException("Deplacement Introuvable"));
    }
}
