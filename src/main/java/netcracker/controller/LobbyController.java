package netcracker.controller;

import netcracker.model.Lobby;
import netcracker.payload.RoomFilter;
import netcracker.payload.RoomDto;
import netcracker.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lobby")
public class LobbyController {


    private final RoomService roomService;

    @Autowired
    public LobbyController(RoomService roomService) {
        this.roomService = roomService;
    }

    private final Lobby lobby = new Lobby();

    @GetMapping
    public ResponseEntity<?> getLobby(){
        lobby.setRoomList(roomService.getRoomList());
        return new ResponseEntity<>(lobby, HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getRoomList() {
        return new ResponseEntity<>(roomService.getRoomList(), HttpStatus.OK);
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto);
    }

    @GetMapping("/rooms/filtered")
    public ResponseEntity<?> getFilteredLobby(@RequestBody RoomFilter roomFilter) {
        return new ResponseEntity<>(roomService.getFilteredRoomList(roomFilter), HttpStatus.OK);
    }
//    @GetMapping("/rooms/sorted")
//    public ResponseEntity<?> getSortedLobby(@RequestBody String sortType) {
//        lobby.setRoomList(roomService.getSortedRoomList(sortType));
//        return new ResponseEntity<>(lobby, HttpStatus.OK);

//    }
}
