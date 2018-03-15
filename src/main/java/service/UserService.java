package service;

import dao.ProjectDao;
import dao.TaskDao;
import dao.UserDao;
import model.Project;
import model.Task;
import model.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserService {

    private UserDao userDao;
    private ProjectDao projectDao;
    private TaskDao taskDao;
    private User user;
    private boolean isLog;




    public UserService(){
        userDao = new UserDao();
        projectDao = new ProjectDao();
        taskDao = new TaskDao();
        isLog=false;

        loadDatabases();
        user=null;
    }

    public boolean isLogged(){
        return isLog;
    }

    private void loadDatabases(){
        projectDao.loadFromDb();
        userDao.loadFromDb();
        taskDao.loadFromDb();
    }

    public User getUser(){
        return user;
    }

    public boolean login(String login, String password){
        Integer userId = userDao.existsByLogin(login);

        if(userId != -1){
            User user = (User) userDao.findById(userId).get();
            boolean passwordsCorrect = Objects.equals(user.getPassword(), password);
            if(passwordsCorrect){
                this.user = user;
                this.isLog = true;
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

    public Optional<List<Project>> getAllProjects(){
        return projectDao.getProjectsListByUserId(user.getId());
    }

    public Integer getProjectIdByProjectName(String projectName){
        if(!getAllProjects().isPresent())
            return -1;
        for (Project project : getAllProjects().get()
             ) {
            if(Objects.equals(project.getProjectName(), projectName))
                return project.getId();
        }
        return -1;
    }

    public Optional<List<Task>> getTasksByProjectId(Integer projectId){
        return taskDao.getTasksListByProjectId(projectId);
    }

    public void saveAll(){
        projectDao.saveToDb();
        userDao.saveToDb();
        taskDao.saveToDb();
    }

    public void logaut(){
        saveAll();
        user = null;
        isLog = false;
    }

    public boolean addProject(String name, String description){
        Project project = new Project.ProjectBuilder()
                .setProjectName(name)
                .setProjectDescription(description)
                .setUserId(user.getId())
                .buildProject();

        projectDao.insert(project);
        return true;
    }

    public boolean addTask(Task task, Integer projectId){
        task.setProjectId(projectId);
        taskDao.insert(task);
        return true;
    }





}
