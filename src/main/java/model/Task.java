package model;

import java.util.Objects;


public class Task {

    public static Integer TASK_ID = 0;

    private Integer id;
    private String taskName;
    private String taskDescription;
    private Integer projectId;


    public Task(Integer id, String taskName, String taskDescription, Integer projectId) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.projectId=projectId;

        TASK_ID++;
    }

    public static class TaskBuilder{
        private Integer id = TASK_ID;
        private String taskName;
        private String taskDescription;
        private Integer projectId;

        public TaskBuilder(){}

        public TaskBuilder setId(Integer id){
            this.id =id;
            return this;
        }

        public TaskBuilder setTaskName(String taskName){
            this.taskName=taskName;
            return this;
        }
        public TaskBuilder setTaskDescription(String taskDescription){
            this.taskDescription=taskDescription;
            return this;
        }

        public TaskBuilder setProjectId(Integer projectId){
            this.projectId = projectId;
            return this;
        }

        public Task buildTask(){
            return new Task(id, taskName, taskDescription, projectId);
        }

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
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
        return id + "~" + projectId + "~" + taskName  + "~" + taskDescription;
    }

    public byte[] toBytes(){
        return toString().getBytes();
    }
}
