package api;

import java.util.List;

public interface Dao {

    void insert(Object value);
    Object findById(Integer id);
    List<Object> selectAll();
    boolean existsById(Integer id);
    void removeById(Integer id);

    void loadFromDb();
    void saveToDb();

}
