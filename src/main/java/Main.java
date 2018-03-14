import dao.ProjectDao;
import dao.TaskDao;
import dao.UserDao;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Project;
import model.Task;
import model.User;
import service.UserService;

import java.util.Optional;

public class Main extends Application{

    Stage mainStage;
    Scene loginScene;
    Scene mainScene;
    Scene registerScene;

    UserService userService = new UserService();

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setTitle("Moqqo - todo list");

        loginScene = initializeLoginScene();
        registerScene = initializeRegisterScene();




        //loginScene = new Scene(grid, 300, 300);
       // mainScene = new Scene(gridHome, 300, 300);


        mainStage.setScene(loginScene);
        mainStage.show();
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

        return new Scene(grid, 500, 500);



    }

    public Scene initializeLoginScene(){
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

        Label passLabel = new Label("Password: ");
        GridPane.setConstraints(passLabel, 0,2);

        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 0, 3);

        Button loginBtn = new Button("Login");
        GridPane.setConstraints(loginBtn, 0,4);

        loginBtn.setOnAction(e->{
            if(userService.login(loginInput.getText(), passInput.getText())) {
                mainStage.setScene(mainScene);
            }
        });

        Button registerBtn = new Button("Sign up");
        GridPane.setConstraints(registerBtn, 1, 4);
        registerBtn.setOnAction(e->{
            mainStage.setScene(registerScene);
        });

        grid.getChildren().addAll(loginLabel, loginInput, passLabel, passInput, loginBtn, registerBtn);

        return new Scene(grid, 500, 500);
    }

}
