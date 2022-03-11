package netcracker.controller;

import netcracker.model.User;
import netcracker.payload.SignUpDto;
import netcracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public @ResponseBody List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public @ResponseBody User getUser(@RequestParam String username){
        return userRepository.findByUsername(username);
    }

//    @PatchMapping("/user/{id}/update")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody SignUpDto signUpDto){
//        return userService.updateUser(id, signUpDto);
//    }

    @DeleteMapping("/user")
    public @ResponseBody User deleteUser(@RequestParam String username){
        return userRepository.findByUsername(username);
    }

}
