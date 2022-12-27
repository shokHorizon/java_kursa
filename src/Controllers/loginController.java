package Controllers;

import Client.App;
import Client.QueryController;
import Models.Users;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {
    @FXML
    private Label label;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField textBoxLogin;

    @FXML
    private PasswordField textBoxPassword;

    @FXML
    void btnSubmitClick(ActionEvent event) {
        String login = textBoxLogin.getText();
        String password = textBoxPassword.getText();
        Packet<Users> userPacket = new Packet<>(
                QueryModel.Users,
                QueryMethod.Read,
                new Users(
                        0,
                        login,
                        password,
                        0
                )
        );
        Users user = (Users) QueryController.query_request(userPacket);
        if (user.getHashedPassword() == null)
            label.setVisible(true);
        label.setVisible(false);

        switch (user.getAccessLevel()){
            case 0->{App.setClientMain();}
            case 1->{App.setManagerMain();}
            case 2->{App.setAdminMain();}
        }
    }

}
