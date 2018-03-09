package api;

import java.util.List;
import java.util.Optional;

public interface Dao {

    int BYTE_END_OF_FILE = 0x95;
    int BYTE_START_OF_RECORD = 0x80;
    int BYTE_END_OF_RECORD = 0x90;
    int BYTE_SEPARATOR_IN_RECORD = 0x7E;
    void insert(Object value);
    Optional<Object> findById(Integer id);
    List<Object> selectAll();
    Integer existsById(Integer id);
    void removeById(Integer id);

    void loadFromDb();
    void saveToDb();

    Object parseRecord(String record);

}
