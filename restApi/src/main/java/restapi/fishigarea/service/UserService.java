package restapi.fishigarea.service;


import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import restapi.fishigarea.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

    UserServiceModel getById(String id) throws NotFoundException;

    List<UserServiceModel> findAllUsers();

    UserServiceModel editAuthority(String id); //promote demote user

    UserServiceModel editUser(UserServiceModel userServiceModel, String oldPassword);

    boolean userWithGivenUsernameExists(String username);
}
