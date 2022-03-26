package netcracker.controller;

import netcracker.payload.ProfileDto;
import netcracker.payload.SignUpDto;
import netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/profile")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal UserDetails userDetails){
        return userService.getUserProfile(userDetails);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        return userService.getUserInfo(userDetails);
    }

    @PutMapping("/user/profile")
    public ResponseEntity<?> updateProfileInfo(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody ProfileDto profileDto){
        return userService.updateUserProfile(userDetails, profileDto);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody SignUpDto signUpDto){
        return userService.updateUserInfo(userDetails, signUpDto);
    }
}
