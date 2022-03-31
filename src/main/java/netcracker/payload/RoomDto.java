package netcracker.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private String name;
    private String password;
    private String scope;
    private UUID gameMasterId;
    private Short maxPlayers;
    private String gameStatus;
}
