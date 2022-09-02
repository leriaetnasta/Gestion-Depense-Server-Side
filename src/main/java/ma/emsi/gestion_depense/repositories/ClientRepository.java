package ma.emsi.gestion_depense.repositories;

import ma.emsi.gestion_depense.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional

public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query("select c from Client c where c.nom like :k")
    List<Client> searchClient(@Param(value="k")String keyword);
}
