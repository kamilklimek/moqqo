import dao.ProjectDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import model.Task;
import service.UserService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application{

    Stage mainStage;
    Scene loginScene;
    Scene mainScene;
    Scene registerScene;
    Scene temp;

    Integer windowWidth;
    Integer windowHeight;


    UserService userService = new UserService();

    public static void main(String[] args) {
       
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

    public GridPane alignMenuGrid(GridPane grid) {
        int row = 0;

        Button projectsListBtn = new Button("Projects list");
        GridPane.setConstraints(projectsListBtn, 0, row++);
        projectsListBtn.setOnAction(e->{
            mainScene = initializeProjectListScene();
            mainStage.setScene(mainScene);
        });


        Button profileBtn = new Button("Profile");
        GridPane.setConstraints(profileBtn, 0, row++);
        profileBtn.setOnAction(e->{
            mainScene = initializeProfileScene();
            mainStage.setScene(mainScene);
        });


        Button newProjectBtn = new Button("New project");
        GridPane.setConstraints(newProjectBtn, 0, row++);
        newProjectBtn.setOnAction(e->{
            mainScene = initializeNewProjectScene();
            mainStage.setScene(mainScene);
        });

        Button newTaskBtn = new Button("New task");
        GridPane.setConstraints(newTaskBtn, 0, row++);
        newTaskBtn.setOnAction(e->{
            mainScene = initializeNewTask();
            mainStage.setScene(mainScene);
        });


        Button logautBtn = new Button("Logout");
        GridPane.setConstraints(logautBtn, 0, row++);
        logautBtn.setOnAction(e->{
            userService.logaut();
            mainScene = initializeLoginScene();
            mainStage.setScene(mainScene);
        });


        grid.getChildren().addAll(
                logautBtn, newProjectBtn, newTaskBtn, profileBtn, projectsListBtn
        );

        return grid;
    }



    public Scene initializeProjectListScene(){

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid = alignMenuGrid(grid);

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

                boolean listIsNotEmpty = userService.getTasksByProjectId(project.getId()).isPresent();
                if(listIsNotEmpty){
                    List<Task> tasks = userService.getTasksByProjectId(project.getId()).get();
                    Label separator1 = new Label("-----------------------");
                    GridPane.setConstraints(separator1, 1, row++);
                    for (Task task : tasks
                         ) {
                        Label labelTaskName = new Label(task.getTaskName());
                        Label labelTaskDescription = new Label("Opis: " + task.getTaskDescription());
                        labelTaskName.setFont(Font.font(14));
                        GridPane.setConstraints(labelTaskName, 1, row++);
                        GridPane.setConstraints(labelTaskDescription, 1, row++);
                        grid.getChildren().addAll(labelTaskName,labelTaskDescription);
                    }

                    Label separator2 = new Label("-----------------------");
                    GridPane.setConstraints(separator2, 1, row++);
                    grid.getChildren().addAll(separator1,separator2);

                }


            }
        }

        grid.getChildren().addAll(projectListLabel);
        return new Scene(grid, windowWidth, windowHeight);
    }

    public Scene initializeNewProjectScene(){
        GridPane grid = new GridPane();
        grid.setHgap(10);

        grid = alignMenuGrid(grid);

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


        Label result = new Label();
        addProjectBtn.setOnAction(e->{
            userService.addProject(projectNameInput.getText(), projectDescriptionInput.getText());
            result.setText("Project " + projectNameInput.getText() + " is created.");
            GridPane.setConstraints(result, 1, 8);

        });

        grid.getChildren().add(result);


        grid.getChildren().addAll(addProjectBtn, projectListLabel, projectNameInput, projectDescriptionInput, projectDescriptionLabel, projectNameLabel);
        return new Scene(grid, windowWidth, windowHeight);


    }

    public Scene initializeNewTask(){

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid = alignMenuGrid(grid);

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
        List<TextField> textFields = new LinkedList<>();

        TextField taskNameInput = new TextField("");
        taskNameInput.setPromptText("Task name");
        GridPane.setConstraints(taskNameInput, 1,row++);


        TextField taskDescriptionInput = new TextField();
        taskDescriptionInput.setPromptText("Description");
        GridPane.setConstraints(taskDescriptionInput, 1,row++);

        Button taskSubmitBtn = new Button("Add task");
        GridPane.setConstraints(taskSubmitBtn, 1, row++);

        ChoiceBox finalProjectsSelect = projectsSelect;

        Label addTaskResult = new Label();
        GridPane.setConstraints(addTaskResult, 1,row++);

        final ChoiceBox choiceBox = projectsSelect;
        taskSubmitBtn.setOnAction(e->{
            String projectName = (String)choiceBox.getValue();
            Integer projectId = userService.getProjectIdByProjectName(projectName);
            Task task = new Task.TaskBuilder()
                    .setTaskName(taskNameInput.getText())
                    .setTaskDescription(taskDescriptionInput.getText())
                    .setProjectId(projectId)
                    .buildTask();
            userService.addTask(task, projectId);
            addTaskResult.setText(task.getTaskName() + " is added to project: " + projectName);


        });

        grid.getChildren().addAll(addTaskResult, profileLabel, projectsSelect, taskDescriptionInput, taskNameInput, taskSubmitBtn);
        return new Scene(grid, windowWidth, windowHeight);
    }

    public Scene initializeProfileScene(){

        GridPane grid = new GridPane();
        grid.setHgap(10);

        grid = alignMenuGrid(grid);


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



        grid.getChildren().addAll(login, email, password,profileLabel);
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
                loginScene = initializeLoginScene();
                mainStage.setScene(loginScene);
            }else{
                System.out.println("Somethings went wrong!");
            }
        });

        Button back = new Button("Back");
        GridPane.setConstraints(back, 0, 7);
        back.setOnAction(e->{

            loginScene = initializeLoginScene();
            mainStage.setScene(loginScene);

        });

        grid.getChildren().addAll(loginLabel, loginInput, emailInput, emailLabel, passLabel, passInput, registerBtn, back);

        return new Scene(grid, windowWidth, windowHeight);



    }

    public Scene initializeLoginScene(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        int row = 0;
        Label loginLabel = new Label("Login: ");
        GridPane.setConstraints(loginLabel, 0, row++);

        TextField loginInput = new TextField();
        loginInput.setPromptText("login");
        GridPane.setConstraints(loginInput, 0, row++);

        Label passLabel = new Label("Password: ");
        GridPane.setConstraints(passLabel, 0,row++);

        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 0, row++);

        Button loginBtn = new Button("Login");
        GridPane.setConstraints(loginBtn, 0,row++);

        Button registerBtn = new Button("Sign up");
        GridPane.setConstraints(registerBtn, 0, row++);
        registerBtn.setOnAction(e->{
            mainStage.setScene(registerScene);

        });
        Label result = new Label();
        GridPane.setConstraints(result, 0,row++);

        loginBtn.setOnAction(e->{
            if(userService.login(loginInput.getText(), passInput.getText())) {
                mainScene = initializeProjectListScene();
                mainStage.setScene(mainScene);
            }else{
                result.setText("Wrong password or login!");
                grid.getChildren().add(result);

            }
        });


        grid.getChildren().addAll(loginLabel, loginInput, passLabel, passInput, loginBtn, registerBtn);

        return new Scene(grid, windowWidth, windowHeight);
    }

}
