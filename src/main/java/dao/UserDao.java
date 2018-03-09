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

public class UserDao implements Dao{

    private final String DB_NAME = "users";
    private List<User> userList;

    public UserDao(){
        userList = new LinkedList<>();
    }

    @Override
    public void loadFromDb(){
        try {
            RandomAccessFile file = new RandomAccessFile(DB_NAME, "r");
            byte sizeOfRecord;
            byte[] bytes;
            Set<String> result = new HashSet<>();
            try{
                while(true){
                    sizeOfRecord = file.readByte();
                    System.out.println(sizeOfRecord);
                    if(sizeOfRecord>0){
                        bytes = new byte[sizeOfRecord];
                        result.add(new String(bytes, "US-ASCII"));
                    }
                }
            }catch(EOFException e){
                for (String s : result
                     ) {
                  //  System.out.println(s.);
                }
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
            for (User user: userList
                 ) {
                bytes = user.toBytes();
                file.write(bytes.length);
                file.write(user.toBytes());
                //file.write(BYTE_END_OF_RECORD);
            }
           // file.write(BYTE_END_OF_FILE);
            file.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object parseRecord(String record) {
        User user = new User();
        return (Object) user;
    }

    @Override
    public void insert(Object user) {
        userList.add((User)user);
    }

    @Override
    public Optional<Object> findById(Integer id) {
        if(existsById(id) == -1)
            return Optional.empty();

        return Optional.of(userList.get(id));
    }


    @Override
    public List<Object> selectAll() {
        return (List)userList;
    }

    @Override
    public Integer existsById(Integer id) {
        for (User user : userList
             ) {
            boolean userExists = Objects.equals(user.getId(), id);
            if(userExists)
                return userList.indexOf(user);
        }
        return -1;
    }

    @Override
    public void removeById(Integer id) {
        if(existsById(id) == -1)
            return;
        userList.remove(id);
    }
}