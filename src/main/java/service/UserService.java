package service;

import dao.ProjectDao;
import dao.TaskDao;
import dao.UserDao;
import model.Project;
import model.Task;
import model.User;

import java.util.Objects;
import java.util.Optional;

public class UserService {

    private UserDao userDao;
    private ProjectDao projectDao;
    private TaskDao taskDao;
    private User user;


    public UserService(){
        userDao = new UserDao();
        projectDao = new ProjectDao();
        taskDao = new TaskDao();

        loadDatabases();
        user=null;
    }

    private void loadDatabases(){
        projectDao.loadFromDb();
        userDao.loadFromDb();
        taskDao.loadFromDb();
    }

    public Optional<User> login(String login, String password){
        Integer userId = userDao.existsByLogin(login);

        System.out.print(userId);
        if(userId != -1){

            System.out.print("TUTAJ2");
            User user = (User) userDao.findById(userId).get();
            boolean passwordsCorrect = user.getPassword().equals(password);
            if(passwordsCorrect)
                return Optional.of(user);
            System.out.print("TUTAJ3");
        }
        return Optional.empty();
    }

    public String register(String login, String email, String password){
        User user = new User.UserBuilder()
                .setLogin(login)
                .setEmail(email)
                .setPassword(password)
                .buildUser();

        userDao.insert(user);

        if(userDao.existsById(user.getId())==-1)
            return "Something went wrong!";

        userDao.saveToDb();
        return "Register succesful!";
    }

    public boolean addProject(Project project){
        return true;
    }

    public boolean addTask(Task task, Integer projectId){

        return true;
    }





}
