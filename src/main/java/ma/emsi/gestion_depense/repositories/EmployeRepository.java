package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.dtos.EmployeProjetDTO;
import ma.emsi.gestion_depense.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
    @Override
    Page<Employe> findAll(Pageable pageable);
    Page<Employe> findByNomContains(String nom, Pageable pageable);
    @Query("select e from Employe e where e.nom like :keyword")
    List<Employe> rechercheEmploye(@Param("keyword") String keyword);

    @Query("select new ma.emsi.gestion_depense.dtos.EmployeProjetDTO(e.nom,p.titre) from Employe e join e.projet p ")
    List<EmployeProjetDTO> findEmployeAndProject();

}
