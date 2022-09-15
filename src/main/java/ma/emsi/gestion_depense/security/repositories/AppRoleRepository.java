package ma.emsi.gestion_depense.security.repositories;

import ma.emsi.gestion_depense.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
