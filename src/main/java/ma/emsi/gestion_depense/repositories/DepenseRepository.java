package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.dtos.DepenseDTO;
import ma.emsi.gestion_depense.entities.Client;
import ma.emsi.gestion_depense.entities.Depense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional

public interface DepenseRepository extends JpaRepository<Depense,Integer> {
    List<Depense> findByDeplacementId(int deplacementId);
    Page<Depense> findByDeplacementId(int deplacementId,Pageable pageable);




}
