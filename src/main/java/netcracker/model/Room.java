package netcracker.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String password;
    private String scope;
    private UUID gameMasterId;
    private UUID deckId;
    private Short maxPlayers;
    private Short currentPlayers;
    private String readyToStart;
    private String gameStatus;
    @ElementCollection
    @CollectionTable(
            name = "UUID",
            joinColumns = @JoinColumn(name = "ROOM_ID")
    )
    @Column(name = "Players_ID")
    private List<UUID> playersId;
}
