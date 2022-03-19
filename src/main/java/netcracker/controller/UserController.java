package netcracker.controller;

import java.security.Principal;
import netcracker.model.User;
import netcracker.payload.ProfileDto;
import netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/profile")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal UserDetails userDetails){
        return userService.getUserProfile(userDetails);
    }

    @PutMapping("/user/profile")
    public ResponseEntity<?> updateProfileInfo(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody ProfileDto profileDto){
        return userService.updateUserProfile(userDetails, profileDto);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        return userService.getUserInfo(userDetails);
    }
}
