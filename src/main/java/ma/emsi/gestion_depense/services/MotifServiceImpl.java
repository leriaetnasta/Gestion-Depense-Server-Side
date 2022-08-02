package ma.emsi.gestion_depense.services;

import ma.emsi.gestion_depense.dtos.MotifDTO;
import ma.emsi.gestion_depense.entities.Motif;
import ma.emsi.gestion_depense.mappers.GestionDepenseMapper;
import ma.emsi.gestion_depense.repositories.MotifRepository;
import ma.emsi.gestion_depense.services.interfaces.MotifService;

import java.util.List;
import java.util.stream.Collectors;

public class MotifServiceImpl implements MotifService {
    MotifRepository motifRepository;
    GestionDepenseMapper gdp;
    @Override
    public List<MotifDTO> listMotif(){
        List<Motif> list= motifRepository.findAll();
        return list.stream().map(motif -> gdp.fromMotif(motif)).collect(Collectors.toList());
    }
}
