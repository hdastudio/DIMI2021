package netcracker.repository;

import netcracker.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByUsername(String name);
}
