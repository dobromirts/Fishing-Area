package restapi.fishigarea.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import restapi.fishigarea.domain.models.service.CatchServiceModel;
import restapi.fishigarea.domain.models.service.UserServiceModel;
import restapi.fishigarea.service.UserService;
import restapi.fishigarea.web.models.request.profile.CatchAddModel;
import restapi.fishigarea.web.models.request.profile.ProfileAddModel;
import restapi.fishigarea.web.models.request.user.UserRegisterRequestModel;
import restapi.fishigarea.web.models.response.profile.CatchResponseModel;
import restapi.fishigarea.web.models.response.profile.ProfileResponseModel;
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

    @PostMapping("/profile/{id}")
    public ResponseEntity editProfile(@PathVariable String id, @RequestPart("profile") ProfileAddModel model, @RequestPart("file") MultipartFile file){
        ProfileAddModel profile=this.modelMapper.map(model,ProfileAddModel.class);
        boolean result= this.userService.editProfile(id, profile, file);
        if (result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/profile/{id}")
    public ProfileResponseModel viewProfile(@PathVariable String id) throws NotFoundException {
        ProfileResponseModel profile=this.modelMapper.map(userService.getById(id).getProfileServiceModel(),ProfileResponseModel.class);
        return profile;
    }

    @PostMapping("/catch/{id}")
    public ResponseEntity addCatch(@PathVariable String id, @RequestPart("catchModel") CatchAddModel model, @RequestPart("file") MultipartFile file){
        CatchAddModel catchModel=this.modelMapper.map(model,CatchAddModel.class);
        boolean result= this.userService.addCatch(id, catchModel, file);
        if (result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/catch/{id}")
    public List<CatchResponseModel> allCatches(@PathVariable String id) throws NotFoundException {
        return this.userService.getAllCatches(id).stream().map(c->this.modelMapper.map(c,CatchResponseModel.class)).collect(Collectors.toList());
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
