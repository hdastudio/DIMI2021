package netcracker.service;

import netcracker.model.Room;
import netcracker.payload.RoomFilter;
import netcracker.payload.RoomDto;
import netcracker.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;


    public ResponseEntity<?> createRoom(RoomDto roomDto) {
        List<Room> roomList = roomRepository.findAll();
        Room room = new Room();
        room.setName(roomDto.getName());
        room.setScope(roomDto.getScope());
        room.setPassword(roomDto.getPassword());
        if (room.getPassword() == null)
            return new ResponseEntity<>("Need password in \"PRIVATE\" room. ", HttpStatus.BAD_REQUEST);
        room.setGameMasterId(roomDto.getGameMasterId());
        room.setMaxPlayers(roomDto.getMaxPlayers());
        room.setCurrentPlayers((short) 0);
        room.setGameStatus(roomDto.getGameStatus());
        roomRepository.save(room);
        roomList.add(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Room> getRoomList() {
        return roomRepository.findAll();
    }

    public List<Room> getFilteredRoomList(RoomFilter roomFilter) {
        Query query = entityManager.createQuery(getQuery(roomFilter));
        List<Room> roomList = new ArrayList<>();
        for(final Object r : query.getResultList()){
            roomList.add((Room) r);
        }
        return roomList;
    }

    public String getQuery(RoomFilter filter){
        return "SELECT * FROM rooms r " +
                "WHERE (r.name = " + filter.getName() + " AND " + filter.getName() + " IS NOT NULL) AND " +
                        "(r.scope = " + filter.getScope() + " AND " + filter.getScope() + " IS NOT NULL) AND " +
                        "(r.max_Players = " + filter.getMaxPlayers() + " AND " + filter.getMaxPlayers() + " IS NOT NULL) AND " +
                        "(r.current_Players = " + filter.getCurrentPlayers() + " AND " + filter.getCurrentPlayers() + " IS NOT NULL)";
    }

//    public List<Room> getSortedRoomList(String sortType) {
//
//    }


}
