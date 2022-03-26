package netcracker.service;

import netcracker.model.Profile;
import netcracker.model.Role;
import netcracker.model.User;
import netcracker.payload.LoginDto;
import netcracker.payload.ProfileDto;
import netcracker.payload.SignUpDto;
import netcracker.repository.RoleRepository;
import netcracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> getUsers(){
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<?> getUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    public ResponseEntity<?> updateUser(UUID id, SignUpDto signUpDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        userRepository.save(user);

        return new ResponseEntity<>("User updated" + user.getId(), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(UUID id){
        if(!userRepository.existsById(id)){
            return new ResponseEntity<>("User not found!",HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> addUserRole(UUID userId, UUID roleId){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        user.addRole(role);
        userRepository.save(user);
        return new ResponseEntity<>("Role added", HttpStatus.OK);
    }

    public ResponseEntity<?> getUserProfile(UserDetails userDetails){
        User user = userRepository.findByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(user.getProfile().getName());
        profileDto.setSurname(user.getProfile().getSurname());
        profileDto.setAboutMeInfo(user.getProfile().getAboutMeInfo());
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    public ResponseEntity<?> updateUserProfile(UserDetails userDetails, ProfileDto profileDto){
        User user = userRepository.findByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        Profile profile = user.getProfile();
        profile.setName(profileDto.getName());
        profile.setSurname(profileDto.getSurname());
        profile.setAboutMeInfo(profileDto.getAboutMeInfo());
        user.setProfile(profile);
        userRepository.save(user);
        return new ResponseEntity<>("User profile updated.", HttpStatus.OK);
    }

    public ResponseEntity<?> signUpUser(SignUpDto signUpDto){

        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Profile profile = new Profile();
        user.setProfile(profile);
        profile.setUser(user);

        Role roles = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(IllegalStateException::new);
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> loginUser(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!" , HttpStatus.OK);
    }

    public ResponseEntity<?> getUserInfo(UserDetails userDetails) {
        User user = userRepository.findByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        SignUpDto userConfInfo = new SignUpDto();
        userConfInfo.setUsername(user.getUsername());
        userConfInfo.setEmail(user.getEmail());
        userConfInfo.setPassword(user.getPassword());

        return new ResponseEntity<>(userConfInfo, HttpStatus.OK);
    }

    public ResponseEntity<?> updateUserInfo(UserDetails userDetails, SignUpDto signUpDto){
        User user = userRepository.findByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        if(userRepository.existsByUsername(signUpDto.getUsername()) || userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signUpDto.getUsername(), signUpDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User conf.info updated", HttpStatus.OK);
    }
}
