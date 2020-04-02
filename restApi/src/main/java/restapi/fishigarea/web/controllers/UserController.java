package restapi.fishigarea.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import restapi.fishigarea.domain.models.service.UserServiceModel;
import restapi.fishigarea.service.UserService;
import restapi.fishigarea.web.models.request.user.UserRegisterRequestModel;
import restapi.fishigarea.web.models.response.user.AllUsersResponeModel;
import restapi.fishigarea.web.models.response.user.UserResponseModel;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterRequestModel model) throws URISyntaxException {
//        return ResponseEntity.ok().body(this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class)));

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password must match!");
//            throw new IllegalArgumentException("Passwords must match!");
        }

        boolean result = userService
                .registerUser(modelMapper
                        .map(model, UserServiceModel.class));
        return ResponseEntity.created(new URI("/users/register")).body(result);
    }

    @GetMapping("/all")
    public List<AllUsersResponeModel> getAllUsers(){
        return this.userService.findAllUsers().stream().map(u->this.modelMapper.map(u,AllUsersResponeModel.class)).collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public UserResponseModel getUserById(@PathVariable(value = "id") String id) throws NotFoundException {
        return modelMapper
                .map(userService.getById(id), UserResponseModel.class);
    }

    @GetMapping(value = "/username/{username}")
    public UserResponseModel getUserByUsername(@PathVariable(value = "username") String username) {
        return modelMapper
                .map(userService.findUserByUsername(username), UserResponseModel.class);
    }

    @GetMapping(value = "/exists/{username}")
    public ResponseEntity checkIfUserExists(@PathVariable String username) {
        boolean exists = userService.userWithGivenUsernameExists(username);

        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(true);
    }

    @PostMapping("/promote")
    public AllUsersResponeModel promoteUser(@RequestParam(name = "id") String id, Principal principal) {

        return modelMapper.map(userService.editAuthority(id),AllUsersResponeModel.class);
    }


}
