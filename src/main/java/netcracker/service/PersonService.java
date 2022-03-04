package netcracker.service;

import netcracker.model.Person;
import netcracker.repository.PersonRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService implements UserDetailsService {
    private PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> getAll(){
        return this.personRepo.findAll();
    }

    public Person getByLogin(String username){
        return this.personRepo.findByUsername(username);
    }

    public Person saveToDb(Person person){
        return personRepo.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person u = getByLogin(username);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }
        return new User(u.getUsername(), u.getPassword(), true, true, true, true, new HashSet());
    }
}
