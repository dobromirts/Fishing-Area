package restapi.fishigarea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.fishigarea.domain.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role findRoleByAuthority(String name);
}
