package ma.emsi.gestion_depense.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.gestion_depense.Exceptions.MotifNotFoundException;
import ma.emsi.gestion_depense.dtos.MotifDTO;
import ma.emsi.gestion_depense.entities.Motif;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.MotifRepository;
import ma.emsi.gestion_depense.services.interfaces.MotifService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.monitor.MonitorSettingException;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional // les operations sont transactionel
@AllArgsConstructor
//journalisation log4j: pour l'utiliser il ya des api comme slf4j; c'est un framework qui permet la journalisation
@Slf4j
public class MotifServiceImpl implements MotifService {
    MotifRepository motifRepository;
    GestionDepenseMapper gdp;
    @Override
    public List<MotifDTO> listMotif(){
        List<Motif> list= motifRepository.findAll();
        return list.stream().map(motif -> gdp.fromMotif(motif)).collect(Collectors.toList());
    }

    @Override
    public MotifDTO saveMotif(MotifDTO motifDTO) {
        log.info("Ajout d'un motif");
        Motif motif= gdp.fromMotifDTO(motifDTO);
        Motif motif1= motifRepository.save(motif);
        return gdp.fromMotif(motif1);
    }



    @Override
    public MotifDTO updateMotif(MotifDTO motifDTO) {
        log.info("edit motif");
        Motif motif= gdp.fromMotifDTO(motifDTO);
        Motif motif1= motifRepository.save(motif);
        return gdp.fromMotif(motif1);
    }


    @Override
    public void deleteMotif(int motifId) {
        log.info("Suppression du motif N°"+motifId);

        motifRepository.deleteById(motifId);
    }


    @Override
    public MotifDTO getMotif(int motifDTO) throws MotifNotFoundException {
        Motif motif= motifRepository.findById(motifDTO).orElseThrow(()-> new MonitorSettingException("Motif Introuvable"));
        return gdp.fromMotif(motif);
    }



}
