package netcracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "user_id")
    private UUID id;
    private String name;
    private String surname;
    private String aboutMeInfo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

//    @ManyToMany
//    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "deck", joinColumns = @JoinColumn(name = "user_id"))
//    List<Deck> deckList;

//    @ManyToMany
//    List<Person> personList;

}
