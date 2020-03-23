package restapi.fishigarea.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import restapi.fishigarea.domain.entities.User;
import restapi.fishigarea.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class GreetingController {
    private final UserRepository userRepository;

    @Autowired
    public GreetingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "username") String username)
            throws IllegalArgumentException {
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new IllegalArgumentException("User not found on :: " + username));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "username") String username, @Valid @RequestBody User userDetails)
            throws IllegalArgumentException {
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new IllegalArgumentException("User not found on :: " + username));
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{username}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "username") String username) throws Exception {
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new IllegalArgumentException("User not found on :: " + username));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//    @DeleteMapping("/user/{username}")
//    public ResponseEntity<?> delete(@PathVariable(value = "username") String username){
//        User user =
//                userRepository
//                        .findByUsername(username)
//                        .orElseThrow(() -> new IllegalArgumentException("User not found on :: " + username));
//        userRepository.delete(user);
//        return ResponseEntity.ok().body("User has been deleted");
//    }

}
