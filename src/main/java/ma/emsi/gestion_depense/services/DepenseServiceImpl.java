package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.DepenseNotFoundException;
import ma.emsi.gestion_depense.Exceptions.DeplacementNotFoundException;
import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.entities.Depense;
import ma.emsi.gestion_depense.entities.Deplacement;
import ma.emsi.gestion_depense.entities.enums.Status;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.DeplacementRepository;
import ma.emsi.gestion_depense.repositories.DepenseRepository;
import ma.emsi.gestion_depense.services.interfaces.DepenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
/* Depense depense= new Depense();
        depense.setMontant(myobj.getDouble("montant"));
        depense.setCommentaire(myobj.getString("commentaire"));
        depense.setTitre(myobj.getString("titre"));
        depense.setStatus(myobj.get);*/
    @Override
    public DepenseDTO saveDepense(DepenseDTO depenseDTO, int idD) throws DepenseNotFoundException, DeplacementNotFoundException {
        log.info("ajout d'une depense");
        Depense depense=gdp.fromDepenseDTO(depenseDTO);
        Deplacement deplacement= deplacementRepository.findById(idD).orElse(null);
        if(deplacement==null) throw new DeplacementNotFoundException("Deplacement not found");
        depense.setDeplacement(deplacement);
        deplacement.getListdepense().add(depense);
        deplacementRepository.save(deplacement);
        Depense depense1= depenseRepository.save(depense);
        return gdp.fromDepense(depense1);
    }

    /*@Override
    public DepenseDTO saveDepense(DepenseDTO depenseDTO) {
        log.info("ajout d'une depense");
        Depense depense=gdp.fromDepenseDTO(depenseDTO);
        Depense depense1= depenseRepository.save(depense);
        return gdp.fromDepense(depense1);
    }*/

    @Override
    public void deleteDepense(int depenseid) {
        log.info("Suppression du depense N°"+depenseid);

        depenseRepository.deleteById(depenseid);
    }


    @Override
    public DepenseDTO updateDepense(DepenseDTO depenseDTO, int idDep) throws DepenseNotFoundException, DeplacementNotFoundException {
        log.info("Edit depense");
        Deplacement deplacement=deplacementRepository.findById(idDep).orElse(null);
        if(deplacement==null) throw new DeplacementNotFoundException("deplacement not found");
        //traitement dial depense not found a ajouter hihiii
        Depense depense=gdp.fromDepenseDTO(depenseDTO);
        deplacement.getListdepense().add(depense);
        depense.setDeplacement(deplacement);
        deplacementRepository.save(deplacement);
        Depense depense1=depenseRepository.save(depense);
        return  gdp.fromDepense(depense1);
    }

    @Override
    public DepenseDTO Accepter(int id) throws DepenseNotFoundException {
        Depense depense= depenseRepository.findById(id).orElseThrow(()-> new DepenseNotFoundException("Deplacement Introuvable"));
        depense.setStatus(Status.ACCEPTE);
        return gdp.fromDepense(depense);
    }
    @Override
    public DepenseDTO Rejeter(int id) throws DepenseNotFoundException {
        Depense depense= depenseRepository.findById(id).orElseThrow(()-> new DepenseNotFoundException("Deplacement Introuvable"));
        depense.setStatus(Status.REJETE);
        return gdp.fromDepense(depense);
    }

    @Override
    public List<DepenseDTO> listDepense() {
        List<Depense> list= depenseRepository.findAll();
        return list.stream().map(depense -> gdp.fromDepense(depense)).collect(Collectors.toList());
    }

    @Override
    public DepenseDTO getDepense(int depenseId) throws DepenseNotFoundException {
        Depense depense=depenseRepository.findById(depenseId).orElseThrow(()-> new DepenseNotFoundException("Depense Introuvable"));
        return gdp.fromDepense(depense);
    }

    @Override
    public byte[] uploadImg(int id) throws DepenseNotFoundException, IOException {
        Depense depense= depenseRepository.findById(id).orElseThrow(()->new DepenseNotFoundException("Depense Introuvable"));
        String photoName= depense.getPieceJustificative();
        File file=new File(System.getProperty("user.home")+"/Gestion_Depense/"+photoName);//user qui a demarrer la session
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);


    }

    @Override
    public void addDeplacementToDepense(int id, int id2) throws DepenseNotFoundException, DeplacementNotFoundException {
        log.info("Operation: Ajout d'un deplacement a un depense");
        Depense depense= depenseRepository.findById(id).orElse(null);
        if(depense==null) throw new DepenseNotFoundException("depense not found");
        Deplacement deplacement=deplacementRepository.findById(id2).orElse(null);
        if(deplacement==null) throw new DeplacementNotFoundException("deplacement not found");
        deplacement.getListdepense().add(depense);
        depense.setDeplacement(deplacement);
        deplacementRepository.save(deplacement);
        depenseRepository.save(depense);
    }
}
