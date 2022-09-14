package ma.emsi.gestion_depense.repositories;


import ma.emsi.gestion_depense.entities.Client;
import ma.emsi.gestion_depense.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional

public interface ProjetRepository extends JpaRepository<Projet,Integer> {
    Page<Projet> findByClientNomOrderByDateDebut(String clientNom, Pageable pageable);
    List<Projet> findByClientId(int clientId);
    Page<Projet> findByClientIdOrderByDateDebut(int clientId, Pageable pageable);
    @Query("select p from Projet p where p.titre like :k")
    List<Projet> searchProjet(@Param(value="k")String keyword);




}
