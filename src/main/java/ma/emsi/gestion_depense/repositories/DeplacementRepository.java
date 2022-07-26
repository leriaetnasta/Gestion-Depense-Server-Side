package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.entities.Deplacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeplacementRepository extends JpaRepository<Deplacement, Integer> {
}
