package netcracker.controller;

import netcracker.model.Person;
import netcracker.model.Role;
import netcracker.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String createAccount(Person person, Map<String, Object> model){
        Person personFromDb = personRepo.findByUsername(person.getUsername());
        if(personFromDb != null){
            model.put("message", "User exists");
            return "signup";
        }
        person.setActive(true);
        person.setRoles(Collections.singleton(Role.USER));
        personRepo.save(person);
        return "redirect:/login";
    }
}
