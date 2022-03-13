package netcracker.controller;

import netcracker.model.User;
import netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public @ResponseBody List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public @ResponseBody User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

//    @PatchMapping("/user/{id}/update")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody SignUpDto signUpDto){
//        return userService.updateUser(id, signUpDto);
//    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
