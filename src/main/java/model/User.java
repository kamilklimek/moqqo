package model;


import java.util.List;
import java.util.Objects;
/**
 * @author maniaq
 * Class holds basic informations of user (user model)
 */
public class User {

    public static Integer USER_ID = 0;

    private Integer id;
    private String email;
    private String login;
    private String password;
    private List<Project> projectList;

    public User(Integer id, String email, String login, String password, List<Project> projectList) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.projectList = projectList;
        USER_ID++;
    }

    public static class UserBuilder{
        private Integer id = USER_ID;
        private String email;
        private String login;
        private String password;
        private List<Project> projectList;

        public UserBuilder setId(Integer id){
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

    public void addProject(Project project){
        projectList.add(project);
    }

    public Integer getId() {
        return id;
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

    @Override
    public String toString(){
        return id + "~" + login + "~" + email  + "~" + password;
    }

    public byte[] toBytes(){
        String str = toString();
        return str.getBytes();
    }
}
