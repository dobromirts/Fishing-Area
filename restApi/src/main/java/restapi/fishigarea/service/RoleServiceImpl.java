package restapi.fishigarea.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.fishigarea.domain.entities.Role;
import restapi.fishigarea.domain.models.service.RoleServiceModel;
import restapi.fishigarea.repository.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count()==0){
            this.roleRepository.save(new Role("ROLE_ROOT"));
            this.roleRepository.save(new Role("ROLE_ADMIN"));
            this.roleRepository.save(new Role("ROLE_USER"));
        }

    }

    @Override
    public Set<RoleServiceModel> findAllAuthorities() {
        return this.roleRepository.findAll().stream().map(r->this.modelMapper.map(r,RoleServiceModel.class)).collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String name) {
        return this.modelMapper.map(this.roleRepository.findRoleByAuthority(name),RoleServiceModel.class);
    }
}
