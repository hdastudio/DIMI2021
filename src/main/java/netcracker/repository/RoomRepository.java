package netcracker.repository;

import netcracker.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "?1", nativeQuery = true)
    List<Room> findAllFiltered(String query);

// Псевдокод
//    @Query(
//            value = "SELECT * FROM rooms r WHERE (r.name = filter.getName AND filter.getName IS NOT NULL) AND " +
//                    "(r.scope = filter.getScope AND filter.getScope IS NOT NULL) AND " +
//                    "(r.max_Players = filter.getMaxPlayers AND filter.getMaxPlayers IS NOT NULL) AND " +
//                    "(r.current_Players = filter.getCurrentPlayers AND filter.getCurrentPlayers IS NOT NULL)",
//            nativeQuery = true)
//    List<Room> findAllFiltered(RoomFilter filter);
}
