package netcracker.service;

import netcracker.exception_handling.NoSuchUserException;
import netcracker.exception_handling.UserIncorrectData;
import netcracker.model.Role;
import netcracker.model.User;
import netcracker.payload.LoginDto;
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
import org.springframework.web.bind.annotation.ExceptionHandler;

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
                .orElseThrow(() -> new NoSuchUserException("User with ID: " + id + " doesn't exists!"));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
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

        Role roles = roleRepository.findByName("ROLE_ADMIN").orElseThrow(IllegalStateException::new);
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> loginUser(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handlerException(NoSuchUserException exception){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
