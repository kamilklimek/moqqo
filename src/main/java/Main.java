import dao.ProjectDao;
import dao.UserDao;
import model.Project;
import model.User;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        ProjectDao projectDao = new ProjectDao();

        projectDao.insert(new Project.ProjectBuilder()
            .setProjectId(1)
            .setProjectName("Kam")
            .setProjectDescription("asdads")
            .setUserId(5)
            .buildProject());


        projectDao.insert(new Project.ProjectBuilder()
                .setProjectId(2)
                .setProjectName("Kam")
                .setProjectDescription("asdads")
                .setUserId(4)
                .buildProject());


        projectDao.insert(new Project.ProjectBuilder()
                .setProjectId(3)
                .setProjectName("Kam")
                .setProjectDescription("asdads")
                .setUserId(4)
                .buildProject());

        projectDao.saveToDb();
    }
}
