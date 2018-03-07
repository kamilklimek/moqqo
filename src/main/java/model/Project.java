package model;

import java.util.Objects;

public class Project {

    private Long id;
    private String projectName;
    private String projectDescription;
    private Long userId;


    public Project(Long id, String projectName, Long userId) {
        this.id = id;
        this.projectName = projectName;
        this.userId = userId;
    }

    public Project(Long id, String projectName, Long userId, String projectDescription) {
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
                Objects.equals(userId, project.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, projectName, projectDescription, userId);
    }
}
