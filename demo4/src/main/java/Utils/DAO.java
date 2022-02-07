package Utils;


import model.Tasks;

import java.util.List;

public interface DAO {
    List<Tasks> get();
    Tasks get(int id);
    boolean save(Tasks tasks);
    boolean delete(int id);
    boolean update(Tasks tasks);
}
