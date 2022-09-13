package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.entities.Deplacement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeplacementRepository extends JpaRepository<Deplacement, Integer> {
    Page<Deplacement> findByEmployeId(int employeId, Pageable pageable);

    @Override
    List<Deplacement> findAllById(Iterable<Integer> integers);
    Page<Deplacement> findByProjetId(int projetId, Pageable pageable);
}
