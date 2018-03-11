package dao;

import api.Dao;
import model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author maniaq
 * Class stands for operations on tasks' database (binary file)
 */

public class TaskDao implements Dao {

    /**
     * name of file where contain tasks informations
     */
    private final String DB_NAME = "tasks";

    /**
     * Map for holding tasks
     * key: projectId
     * value: Task
     */
    private Map<Integer, Task> tasks;

    public TaskDao(){
        tasks = new HashMap<>();
    }

    @Override
    public void insert(Object value) {

    }

    @Override
    public Optional<Object> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Map<Integer, Object> selectAll() {
        return null;
    }

    @Override
    public Integer existsById(Integer id) {
        return null;
    }

    @Override
    public void removeById(Integer id) {

    }

    @Override
    public void loadFromDb() {

    }

    @Override
    public void saveToDb() {

    }

    @Override
    public Object parseRecord(String record) {
        return null;
    }
}
