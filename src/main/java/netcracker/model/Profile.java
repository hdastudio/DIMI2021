package netcracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "user_id")
    private Long id;
    @Null
    private String name;
    @Null
    private String surname;
    @Null
    private String aboutMeInfo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

//    @ManyToMany
//    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "deck", joinColumns = @JoinColumn(name = "user_id"))
//    List<String> deckList;

//    @ManyToMany
//    List<String> personList;

}
