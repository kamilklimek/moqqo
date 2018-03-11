package service;

import dao.ProjectDao;
import dao.TaskDao;
import dao.UserDao;
import model.Project;
import model.Task;
import model.User;

import java.util.Optional;

public class UserService {

    private UserDao userDao;
    private ProjectDao projectDao;
    private TaskDao taskDao;
    private User user;


    public UserService(){
        user=null;
    }

    public User login(){

        return new User();
    }

    public boolean register(){
        return true;
    }

    public boolean addProject(Project project){
        return true;
    }

    public boolean addTask(Task task, Integer projectId){

        return true;
    }





}
