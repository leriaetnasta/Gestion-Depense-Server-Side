package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
    @Override
    Page<Employe> findAll(Pageable pageable);
    Page<Employe> findByNomContains(String nom, Pageable pageable);

}
