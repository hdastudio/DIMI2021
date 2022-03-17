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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

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

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public ResponseEntity<?> updateUser(Long id, SignUpDto signUpDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        userRepository.save(user);

        return new ResponseEntity<>("User updated" + user.getId(), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(Long id){
        if(!userRepository.existsById(id)){
            return new ResponseEntity<>("User not found!",HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> addUserRole(Long userId, Long roleId){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        user.addRole(role);
        userRepository.save(user);
        return new ResponseEntity<>("Role added", HttpStatus.OK);
    }

    public ResponseEntity<?> getUserProfile(Long id){

        if(!userRepository.existsById(id)){
            return new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
        }
        Profile profile = userRepository.getById(id).getProfile();
        return new ResponseEntity<>(profile, HttpStatus.OK);
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

        Role roles = roleRepository.findByName("ROLE_USER").orElseThrow(IllegalStateException::new);
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

}
