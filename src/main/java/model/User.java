package model;


import java.util.Objects;

public class User {

    private Long id;
    private String email;
    private String login;
    private String password;
    private List<Project> projectList;

    public User(Long id, String email, String login, String password, List<Project> projectList) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.projectList = projectList;
    }

    private static class UserBuilder{
        private Long id;
        private String email;
        private String login;
        private String password;
        private List<Project> projectList;

        public UserBuilder setId(Long id){
            this.id=id;
            return this;
        }

        public UserBuilder setEmail(String email){
            this.email=email;
            return this;
        }

        public UserBuilder setLogin(String login){
            this.login=login;
            return this;
        }

        public UserBuilder setPassword(String password){
            this.password=password;
            return this;
        }

        public UserBuilder setProjectList(List<Project> projectList){
            this.projectList=projectList;
            return this;
        }

        public User buildUser(){
            return new User(id, email, login, password, projectList);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(projectList, user.projectList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, login, password, projectList);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
