package ma.emsi.gestion_depense.repositories;


import ma.emsi.gestion_depense.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface ProjetRepository extends JpaRepository<Projet,Integer> {
}
