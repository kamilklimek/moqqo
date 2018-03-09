import dao.UserDao;
import model.User;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.insert(new User.UserBuilder()
                .setEmail("example")
                .setId(2)
                .setLogin("amg")
                .setPassword("pass")
                //.setProjectList(new List<Project>)
                .buildUser());
        userDao.insert(new User.UserBuilder()
                .setEmail("example")
                .setId(2)
                .setLogin("amg")
                .setPassword("pass")
                //.setProjectList(new List<Project>)
                .buildUser());
        userDao.insert(new User.UserBuilder()
                .setEmail("example")
                .setId(2)
                .setLogin("amg")
                .setPassword("pass")
                //.setProjectList(new List<Project>)
                .buildUser());
        userDao.insert(new User.UserBuilder()
                .setEmail("example")
                .setId(2)
                .setLogin("amg")
                .setPassword("pass")
                //.setProjectList(new List<Project>)
                .buildUser());
        userDao.saveToDb();
        userDao.loadFromDb();
    }
}
