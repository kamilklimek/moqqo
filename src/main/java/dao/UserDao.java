package dao;

import api.Dao;

import java.util.List;

public class UserDao implements Dao{

    private final String DB_NAME = "users";

    @Override
    public void loadFromDb(){

    }

    @Override
    public void saveToDb(){

    }

    @Override
    public void insert(Object user) {

    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public List<Object> selectAll() {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public void removeById(Integer id) {

    }
}