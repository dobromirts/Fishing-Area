package restapi.fishigarea.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import restapi.fishigarea.constants.AuthorityConstants;
import restapi.fishigarea.domain.entities.User;
import restapi.fishigarea.domain.models.RoleServiceModel;
import restapi.fishigarea.domain.models.service.user.UserServiceModel;
import restapi.fishigarea.repository.UserRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final static String USERNAME_NOT_FOUND_MESSAGE="Username not found!";
    private final static String NO_SUCH_USERNAME_MESSAGE="No such username";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE));
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        this.roleService.seedRolesInDb();

        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(this.roleService.findAllAuthorities());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        try {
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public UserServiceModel findUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(NO_SUCH_USERNAME_MESSAGE));
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getById(String id) throws NotFoundException {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No such id"));

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel editUser(UserServiceModel userServiceModel, String oldPassword) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No such username"));

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect old password!");
        }
        user.setPassword(userServiceModel.getPassword() != null ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        user.setEmail(userServiceModel.getEmail());


        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> {
                    UserServiceModel userServiceModel = this.modelMapper.map(u, UserServiceModel.class);
                    userServiceModel.setAuthorities(u.getAuthorities().stream()
                            .map(a -> this.modelMapper.map(a, RoleServiceModel.class)).collect(Collectors.toSet()));
                    return userServiceModel;
                }).collect(Collectors.toList());
    }


    @Override
    public void editAuthority(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(NO_SUCH_USERNAME_MESSAGE));
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        boolean hasAuthority = false;

        for (RoleServiceModel authority : userServiceModel.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                hasAuthority = true;
            }
        }

        if (hasAuthority) {
            userServiceModel.getAuthorities().clear();
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        } else {
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
        }

        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }







    private String getUserAuthority(String userId) throws NotFoundException {

        RoleServiceModel user = roleService.findByAuthority(AuthorityConstants.AUTHORITY_USER);
        RoleServiceModel admin = roleService.findByAuthority(AuthorityConstants.AUTHORITY_ADMIN);
        RoleServiceModel rootAdmin = roleService.findByAuthority(AuthorityConstants.AUTHORITY_ROOT);

        Set<RoleServiceModel> authorities = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("No such user with given id"))
                .getAuthorities().stream().map(r->this.modelMapper.map(r,RoleServiceModel.class)).collect(Collectors.toSet());

        if (authorities.contains(rootAdmin)) {
            return rootAdmin.getAuthority();
        } else if (authorities.contains(admin)) {
            return admin.getAuthority();
        }else if (authorities.contains(user)) {
            return user.getAuthority();
        } else {
            throw new IllegalArgumentException("No such role");
        }
    }

//    private String extractAuthority(String userId) throws NotFoundException {
//
//        String authority = getUserAuthority(userId);
//
//        return CaseFormat.UPPER_UNDERSCORE.to(
//                CaseFormat.LOWER_CAMEL, authority);
//    }
}
