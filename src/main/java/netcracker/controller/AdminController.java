package netcracker.controller;

import netcracker.model.User;
import netcracker.payload.ProfileDto;
import netcracker.payload.SignUpDto;
import netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Long id){
        return userService.getUser(id);
    }

    @PutMapping("users/{id}/role/{roleId}")
    public ResponseEntity<?> addUserRole(@PathVariable(name = "id") Long userId,
                                         @PathVariable(name = "roleId") Long roleId){

        return userService.addUserRole(userId, roleId);
    }

    @PutMapping("/users/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody SignUpDto signUpDto){
        return userService.updateUser(id, signUpDto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
