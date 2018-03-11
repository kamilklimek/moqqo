package dao;

import api.Dao;
import model.Project;
import model.User;
import sun.nio.cs.UTF_32;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author maniaq
 * Class stands for operations on users' database (binary file)
 */

public class UserDao implements Dao{

    /**
     * name of file where contain users informations
     */
    private final String DB_NAME = "users";
    /**
     * Map for holding users
     * Key: UserId
     * Value: User
     */
    private Map<Integer, User> users;

    public UserDao(){
        users = new HashMap<>();
    }

    @Override
    public void loadFromDb(){
        try {
            RandomAccessFile file = new RandomAccessFile(DB_NAME, "r");
            byte sizeOfRecord;
            byte[] bytes;
            try{
                while((sizeOfRecord = file.readByte()) != BYTE_END_OF_FILE){
                    if(sizeOfRecord>0){
                        bytes = new byte[sizeOfRecord];
                        file.read(bytes);
                        User user = (User)parseRecord(new String(bytes));
                        users.put(user.getId(), user);
                    }
                }
            }catch(EOFException e){

            }
            file.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveToDb(){
        try {
            byte[] bytes;
            RandomAccessFile file = new RandomAccessFile(DB_NAME, "rw");
            for (Map.Entry<Integer, User> user: users.entrySet()) {
                bytes = user.getValue().toBytes();
                file.write(bytes.length);
                file.write(user.getValue().toBytes());
            }
            file.write(BYTE_END_OF_FILE);
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object parseRecord(String record) {
        String [] userInformations = record.split("~");
        User user = new User.UserBuilder()
                .setId(Integer.parseInt(userInformations[0]))
                .setLogin(userInformations[1])
                .setEmail(userInformations[2])
                .setPassword(userInformations[3])
                .buildUser();
        return (Object) user;
    }

    @Override
    public void insert(Object user) {
        User tempUser = (User)user;

        users.put(tempUser.getId(), tempUser);
        saveToDb();
    }

    @Override
    public Optional<Object> findById(Integer id) {
        if(existsById(id) == -1)
            return Optional.empty();

        return Optional.of(users.containsKey(id));
    }


    @Override
    public Map<Integer, Object> selectAll() {
        return (Map)users;
    }

    @Override
    public Integer existsById(Integer id) {
        if(!users.containsKey(id))
            return -1;
        return id;
    }

    @Override
    public void removeById(Integer id) {
        if(existsById(id) == -1)
            return;
        users.remove(id);
        saveToDb();
    }
}