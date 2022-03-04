package netcracker.controller;

import netcracker.model.Person;
import netcracker.model.Role;
import netcracker.service.PersonService;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private PersonService personService;

    public LoginController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Person createPerson(Person person, Map<String, Object> model){
        person.setActive(true);
        person.setRoles(Collections.singleton(Role.USER));
        Person registeredPerson = personService.saveToDb(person);
        return registeredPerson;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Person getAuthPerson(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            return null;
        }
        Object principal = auth.getPrincipal();
        Person person = (principal instanceof Person) ? (Person) principal : null;
        return Objects.nonNull(person) ? this.personService.getByLogin(person.getUsername()) : null;
    }
}
