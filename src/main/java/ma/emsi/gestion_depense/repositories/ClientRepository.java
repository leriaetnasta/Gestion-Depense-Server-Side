package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
