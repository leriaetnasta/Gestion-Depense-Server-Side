package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.entities.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface DepenseRepository extends JpaRepository<Depense,Integer> {
}
