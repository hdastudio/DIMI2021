package netcracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
