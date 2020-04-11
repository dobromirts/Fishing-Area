package restapi.fishigarea.service;


import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.CatchServiceModel;
import restapi.fishigarea.domain.models.service.UserServiceModel;
import restapi.fishigarea.web.models.request.profile.CatchAddModel;
import restapi.fishigarea.web.models.request.profile.ProfileAddModel;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

    UserServiceModel getById(String id) throws NotFoundException;

    List<UserServiceModel> findAllUsers();

    UserServiceModel editAuthority(String id); //promote demote user

    UserServiceModel editUser(UserServiceModel userServiceModel, String oldPassword);

    List<CatchServiceModel> getAllCatches(String id);

    boolean editProfile(String id, ProfileAddModel profileAddModel, MultipartFile file);

    boolean addCatch(String id, CatchAddModel catchModel, MultipartFile file);

    boolean userWithGivenUsernameExists(String username);

    boolean userWithGivenEmailExists(String email);
}
