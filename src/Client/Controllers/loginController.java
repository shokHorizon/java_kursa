package Client.Controllers;

import Client.App;
import Client.QueryController;
import Models.Users;
import Protocols.Crypto;
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
                null,
                new Users(
                        0,
                        login,
                        Crypto.hashString(password),
                        0
                )
        );
        Users user = (Users) QueryController.query_request(userPacket).get(0);
        //System.out.println(user.toString());
        label.setVisible(false);
        if (user.getAccessLevel() == -1)
            label.setVisible(true);
        else
            App.token = user.getHashedPassword();


        switch (user.getAccessLevel()){
            case 0->{App.setClientMain();}
            case 1->{App.setManagerMain();}
            case 2->{App.setAdminMain();}
        }
    }

}
