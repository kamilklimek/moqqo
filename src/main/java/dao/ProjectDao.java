package dao;

import api.Dao;

import java.util.List;
import java.util.Optional;

public class ProjectDao implements Dao {
    @Override
    public void insert(Object value) {

    }

    @Override
    public Optional<Object> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Object> selectAll() {
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
