package application;

import application.archives.User;
import application.archives.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public Button btnLogin;
    @FXML
    public TextField txtUserName;

    public void btnLoginClick(ActionEvent actionEvent) {

        UserManager userManager = new UserManager();
        User user = userManager.findUserbyName(txtUserName.getText());
        userManager.setActiveUser(user);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Program.fxml"));
            Parent root = fxmlLoader.load();
            ProgramController controller = fxmlLoader.<ProgramController>getController();
            controller.initializeGUI(userManager);
            Stage stage = new Stage();
            String title = String.format("Library App " +  "   " + "User Loggin: %s", txtUserName.getText());
            stage.setTitle(title);
            stage.setScene(new Scene(root, 700 , 500));
            stage.setResizable(false);
            stage.show();

            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
