package Client;

import Client.Controllers.adminController;
import Client.Controllers.clientBooksController;
import Client.Controllers.clientMainController;
import Client.Controllers.managerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    private static Parent loginPage;
    private static Parent clientMain;
    private static Parent clientBooks;
    private static Parent adminMain;
    private static Parent managerMain;
    private static Stage AppStage;
    public static int token = 0;
    public static Scene scene;

    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        AppStage = stage;
        try {
            loginPage = FXMLLoader.load(getClass().getResource("login.fxml"));
            clientMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("client_main.fxml")));
            clientBooks = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("client_books.fxml")));
            adminMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin_main.fxml")));
            managerMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("manager_main.fxml")));
        } catch (IOException e){
            e.printStackTrace();
        }

        scene = new Scene(loginPage,600,400);
        stage.setScene(scene);
        stage.show();
    }

    private static void setScene(Parent parent){
        scene.setRoot(parent);
    }

    public static void setLoginPage(){setScene(loginPage);}
    public static void setClientMain(){
        setScene(clientMain);
        clientMainController.instance.updateTravels(null);
    }
    public static void setClientBooks(){
        setScene(clientBooks);
        clientBooksController.instance.updateBooks();
    }
    public static void setManagerMain(){
        setScene(managerMain);
        managerController.instance.switchToTravels();
    }
    public static void setAdminMain(){
        setScene(adminMain);
        adminController.instance.switchToTravels();
    }


}
