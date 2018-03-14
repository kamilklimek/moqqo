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

    public boolean login(String login, String password){
        Integer userId = userDao.existsByLogin(login);

        if(userId != -1){
            User user = (User) userDao.findById(userId).get();
            boolean passwordsCorrect = Objects.equals(user.getPassword(), password);
            if(passwordsCorrect){
                this.user = user;
                return true;
            }

        }
        return false;
    }

    public boolean register(String login, String email, String password){
        User user = new User.UserBuilder()
                .setLogin(login)
                .setEmail(email)
                .setPassword(password)
                .buildUser();

        userDao.insert(user);

        if(userDao.existsById(user.getId())==-1)
            return false;

        userDao.saveToDb();
        return true;
    }

    public boolean addProject(Project project){
        project.setUserId(user.getId());
        projectDao.insert(project);
        return true;
    }

    public boolean addTask(Task task, Integer projectId){
        task.setProjectId(projectId);
        taskDao.insert(task);
        return true;
    }





}
