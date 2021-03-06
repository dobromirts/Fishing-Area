package restapi.fishigarea.service;

import restapi.fishigarea.domain.models.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();

    Set<RoleServiceModel> findAllAuthorities();

    RoleServiceModel findByAuthority(String name);
}
