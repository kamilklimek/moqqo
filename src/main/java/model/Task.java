package model;

import java.util.Objects;

public class Task {

    private Long id;
    private String taskName;
    private String taskDescription;
    private Long projectId;

    public Task(Long id, String taskName, Long projectId) {
        this.id = id;
        this.taskName = taskName;
        this.projectId=projectId;
    }

    public Task(Long id, String taskName, String taskDescription, Long projectId) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.projectId=projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(taskDescription, task.taskDescription) &&
                Objects.equals(projectId, task.projectId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, taskName, taskDescription, projectId);
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    @Override
    public String toString(){
        return id + "~" + projectId + "~" + taskName  + "~" + taskDescription + "\n";
    }
}
