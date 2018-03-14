import dao.ProjectDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Project;
import model.User;
import service.UserService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application{

    Stage mainStage;
    Scene loginScene;
    Scene mainScene;
    Scene registerScene;

    Integer windowWidth;
    Integer windowHeight;


    UserService userService = new UserService();

    public static void main(String[] args) {
        Project project = new Project.ProjectBuilder()
                .setUserId(0)
                .setProjectName("hi2")
                .setProjectDescription("hi22")
                .buildProject();

        Project project2 = new Project.ProjectBuilder()
                .setUserId(0)
                .setProjectName("elo")
                .setProjectDescription("elo")
                .buildProject();

        ProjectDao projectDao = new ProjectDao();
        projectDao.insert(project2);
        projectDao.insert(project);
        projectDao.saveToDb();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        windowWidth = 500;
        windowHeight = 500;
        mainStage = stage;
        mainStage.setTitle("Moqqo - todo list");

        loginScene = initializeLoginScene();
        registerScene = initializeRegisterScene();


        mainStage.setScene(loginScene);
        mainStage.show();
    }



    public Scene initializeProjectListScene(){

        GridPane grid = new GridPane();
        grid.setHgap(10);

        Button projectList = new Button("Projects list");
        GridPane.setConstraints(projectList, 0, 0);
        projectList.setOnAction(e->{
            mainScene = initializeProjectListScene();
            mainStage.setScene(mainScene);
        });


        Button profile = new Button("Profile");
        GridPane.setConstraints(profile, 0, 1);
        profile.setOnAction(e->{
            mainScene = initializeProfileScene();
            mainStage.setScene(mainScene);
        });

        Button logautBtn = new Button("Logout");
        GridPane.setConstraints(logautBtn, 0, 2);
        logautBtn.setOnAction(e->{
            userService.logaut();
            mainScene = initializeLoginScene();
            mainStage.setScene(mainScene);
        });

        Button newProject = new Button("New project");
        GridPane.setConstraints(newProject, 0, 3);
        newProject.setOnAction(e->{
            mainScene = initializeNewProjectScene();
            mainStage.setScene(mainScene);
        });
        Button newTask = new Button("New task");
        GridPane.setConstraints(newTask, 0, 4);
        newTask.setOnAction(e->{
            mainScene = initializeNewTask();
            mainStage.setScene(mainScene);
        });



        Label projectListLabel = new Label("Projects: ");
        projectListLabel.setFont(Font.font(25));
        GridPane.setConstraints(projectListLabel, 1, 0);

        boolean ifListProjectIsNotEmpty = userService.getAllProjects().isPresent();
        if(ifListProjectIsNotEmpty){
            Integer row = 1;
            for (Project project : userService.getAllProjects().get()
                 ) {
                Label labelProject = new Label(project.getProjectName());
                Label labelDescription = new Label("Opis: " + project.getProjectDescription());
                labelProject.setFont(Font.font(16));
                GridPane.setConstraints(labelProject, 1, row++);
                GridPane.setConstraints(labelDescription, 1, row++);
                grid.getChildren().addAll(labelProject,labelDescription);
            }
        }

        grid.getChildren().addAll(projectList,profile,logautBtn, projectListLabel, newProject, newTask);
        return new Scene(grid, windowWidth, windowHeight);
    }

    public Scene initializeNewProjectScene(){
        GridPane grid = new GridPane();
        grid.setHgap(10);

        Button projectList = new Button("Projects list");
        GridPane.setConstraints(projectList, 0, 0);
        projectList.setOnAction(e->{
            mainScene = initializeProjectListScene();
            mainStage.setScene(mainScene);
        });


        Button profile = new Button("Profile");
        GridPane.setConstraints(profile, 0, 1);
        profile.setOnAction(e->{
            mainScene = initializeProfileScene();
            mainStage.setScene(mainScene);
        });

        Button logautBtn = new Button("Logout");
        GridPane.setConstraints(logautBtn, 0, 2);
        logautBtn.setOnAction(e->{
            userService.logaut();
            mainScene = initializeLoginScene();
            mainStage.setScene(mainScene);
        });

        Button newProject = new Button("New project");
        GridPane.setConstraints(newProject, 0, 3);
        newProject.setOnAction(e->{
            mainScene = initializeNewProjectScene();
            mainStage.setScene(mainScene);
        });
        Button newTask = new Button("New task");
        GridPane.setConstraints(newTask, 0, 4);
        newTask.setOnAction(e->{
            mainScene = initializeNewTask();
            mainStage.setScene(mainScene);
        });


        Label projectListLabel = new Label("New project: ");
        projectListLabel.setFont(Font.font(25));
        GridPane.setConstraints(projectListLabel, 1, 0);
        Integer row = 1;

        Label projectNameLabel = new Label("Project name: ");
        TextField projectNameInput = new TextField();
        projectNameInput.setPromptText("Project name");
        GridPane.setConstraints(projectNameLabel, 1, row++);
        GridPane.setConstraints(projectNameInput, 1, row++);

        Label projectDescriptionLabel = new Label("Project description: ");
        TextField projectDescriptionInput = new TextField();
        projectDescriptionInput.setPromptText("Project description");
        GridPane.setConstraints(projectDescriptionLabel, 1, row++);
        GridPane.setConstraints(projectDescriptionInput, 1, row++);

        Button addProjectBtn = new Button("Add project");
        GridPane.setConstraints(addProjectBtn, 1, row++);

        addProjectBtn.setOnAction(e->{
            userService.addProject(projectNameInput.getText(), projectDescriptionInput.getText());
            Label result = new Label("Project " + projectNameInput.getText() + " was created.");
            GridPane.setConstraints(result, 1, 8);
            grid.getChildren().add(result);
        });




        grid.getChildren().addAll(projectList,profile,logautBtn,addProjectBtn, projectListLabel,newTask, newProject, projectNameInput, projectDescriptionInput, projectDescriptionLabel, projectNameLabel);
        return new Scene(grid, windowWidth, windowHeight);


    }

    public Scene initializeNewTask(){

        GridPane grid = new GridPane();
        grid.setHgap(10);

        Button projectList = new Button("Projects list");
        GridPane.setConstraints(projectList, 0, 0);
        projectList.setOnAction(e->{
            mainScene = initializeProjectListScene();
            mainStage.setScene(mainScene);
        });


        Button profile = new Button("Profile");
        GridPane.setConstraints(profile, 0, 1);
        profile.setOnAction(e->{
            mainScene = initializeProfileScene();
            mainStage.setScene(mainScene);
        });

        Button logautBtn = new Button("Logout");
        GridPane.setConstraints(logautBtn, 0, 2);
        logautBtn.setOnAction(e->{
            userService.logaut();
            mainScene = initializeLoginScene();
            mainStage.setScene(mainScene);
        });

        Button newProject = new Button("New project");
        GridPane.setConstraints(newProject, 0, 3);
        newProject.setOnAction(e->{
            mainScene = initializeNewProjectScene();
            mainStage.setScene(mainScene);
        });
        Button newTask = new Button("New task");
        GridPane.setConstraints(newTask, 0, 4);
        newTask.setOnAction(e->{
            mainScene = initializeNewTask();
            mainStage.setScene(mainScene);
        });


        Label profileLabel = new Label("New task");
        profileLabel.setFont(Font.font(25));
        GridPane.setConstraints(profileLabel, 1, 0);

        Integer row = 1;

        Label projects = new Label("Choose project: ");
        GridPane.setConstraints(projects, 1, row++);
        ChoiceBox projectsSelect = new ChoiceBox(FXCollections.observableArrayList(
                "(none)"));

        if(userService.getAllProjects().isPresent()){
            List<String> projectNames = new LinkedList<>();
            for (Project project :  userService.getAllProjects().get())
                  {
                projectNames.add(project.getProjectName());
            }
            projectsSelect = new ChoiceBox(FXCollections.observableArrayList(projectNames));
        }

        GridPane.setConstraints(projectsSelect, 1, row++);

        grid.getChildren().addAll(projectList,profile,logautBtn, profileLabel, newProject, projectsSelect, newTask);
        return new Scene(grid, windowWidth, windowHeight);
    }

    public Scene initializeProfileScene(){

        GridPane grid = new GridPane();
        grid.setHgap(10);

        Button projectList = new Button("Projects list");
        GridPane.setConstraints(projectList, 0, 0);
        projectList.setOnAction(e->{
            mainScene = initializeProjectListScene();
            mainStage.setScene(mainScene);
        });


        Button profile = new Button("Profile");
        GridPane.setConstraints(profile, 0, 1);
        profile.setOnAction(e->{
            mainScene = initializeProfileScene();
            mainStage.setScene(mainScene);
        });

        Button logautBtn = new Button("Logout");
        GridPane.setConstraints(logautBtn, 0, 2);
        logautBtn.setOnAction(e->{
            userService.logaut();
            mainScene = initializeLoginScene();
            mainStage.setScene(mainScene);
        });

        Button newProject = new Button("New project");
        GridPane.setConstraints(newProject, 0, 3);
        newProject.setOnAction(e->{
            mainScene = initializeNewProjectScene();
            mainStage.setScene(mainScene);
        });
        Button newTask = new Button("New task");
        GridPane.setConstraints(newTask, 0, 4);
        newTask.setOnAction(e->{
            mainScene = initializeNewTask();
            mainStage.setScene(mainScene);
        });


        Label profileLabel = new Label("Your profile: ");
        profileLabel.setFont(Font.font(25));
        GridPane.setConstraints(profileLabel, 1, 0);

        Integer row = 1;
        User user = userService.getUser();
        Label login = new Label("Login: " + user.getLogin());
        Label email = new Label("Email: " + user.getEmail());
        Label password = new Label("Password: "+user.getPassword());

        GridPane.setConstraints(login, 1, row++);
        GridPane.setConstraints(email, 1, row++);
        GridPane.setConstraints(password, 1, row++);



        grid.getChildren().addAll(projectList,profile,logautBtn, login, email, password, newTask,profileLabel, newProject);
        return new Scene(grid, windowWidth, windowHeight);
    }

    public Scene initializeRegisterScene(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label loginLabel = new Label("Login: ");
        GridPane.setConstraints(loginLabel, 0, 0);

        TextField loginInput = new TextField();
        loginInput.setPromptText("login");
        GridPane.setConstraints(loginInput, 0, 1);

        Label emailLabel = new Label("Adres e-mail: ");
        GridPane.setConstraints(emailLabel, 0, 2);

        TextField emailInput = new TextField();
        loginInput.setPromptText("email");
        GridPane.setConstraints(emailInput, 0, 3);

        Label passLabel = new Label("Password: ");
        GridPane.setConstraints(passLabel, 0,4);

        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 0, 5);

        Button registerBtn = new Button("Register");
        GridPane.setConstraints(registerBtn, 0,6);

        registerBtn.setOnAction(e->{
            String login = loginInput.getText();
            String email = emailInput.getText();
            String password = passInput.getText();

            boolean registerSuccesful = userService.register(login, email, password);
            if(registerSuccesful){
                System.out.println("Zarejestrowany");
            }else{
                System.out.println("Cos poszło nie tak");
            }
        });

        grid.getChildren().addAll(loginLabel, loginInput, emailInput, emailLabel, passLabel, passInput, registerBtn);

        return new Scene(grid, windowWidth, windowHeight);



    }

    public Scene initializeLoginScene(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label loginLabel = new Label("Login: ");
        GridPane.setConstraints(loginLabel, 0, 0);

        TextField loginInput = new TextField();
        loginInput.setPromptText("login");
        GridPane.setConstraints(loginInput, 0, 1);

        Label passLabel = new Label("Password: ");
        GridPane.setConstraints(passLabel, 0,2);

        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 0, 3);

        Button loginBtn = new Button("Login");
        GridPane.setConstraints(loginBtn, 0,4);

        loginBtn.setOnAction(e->{
            if(userService.login(loginInput.getText(), passInput.getText())) {
                mainScene = initializeProjectListScene();
                mainStage.setScene(mainScene);
            }
        });

        Button registerBtn = new Button("Sign up");
        GridPane.setConstraints(registerBtn, 0, 5);
        registerBtn.setOnAction(e->{
            mainStage.setScene(registerScene);
        });

        grid.getChildren().addAll(loginLabel, loginInput, passLabel, passInput, loginBtn, registerBtn);

        return new Scene(grid, windowWidth, windowHeight);
    }

}
