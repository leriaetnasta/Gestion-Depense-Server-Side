package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.entities.Motif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface MotifRepository extends JpaRepository<Motif,Integer> {
}
