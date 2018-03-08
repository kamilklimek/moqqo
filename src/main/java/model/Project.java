package model;

import java.util.List;
import java.util.Objects;

public class Project {

    private Integer id;
    private String projectName;
    private String projectDescription;
    private Integer userId;
    private List<Task> taskList;


    public Project(Integer id, String projectName, Integer userId) {
        this.id = id;
        this.projectName = projectName;
        this.userId = userId;
    }

    public Project(Integer id, String projectName, Integer userId, String projectDescription) {
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.userId = userId;
    }

    public Project(Integer id, String projectName, String projectDescription, Integer userId, List<Task> taskList) {
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.userId = userId;
        this.taskList = taskList;
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(projectDescription, project.projectDescription) &&
                Objects.equals(userId, project.userId) &&
                Objects.equals(taskList, project.taskList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, projectName, projectDescription, userId, taskList);
    }

    @Override
    public String toString(){
        return id + "~" + userId + "~" + projectName  + "~" + projectDescription + "~" + taskList.toString() +"\n";
    }
}
