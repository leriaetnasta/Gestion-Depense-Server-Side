package ma.emsi.gestion_depense.security.repositories;

import ma.emsi.gestion_depense.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
