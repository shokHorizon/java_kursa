package Client;

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

    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        AppStage = stage;
        try {
            loginPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            clientMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("client_main.fxml")));
            clientBooks = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("client_books.fxml")));
            adminMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminMain.fxml")));
            managerMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("managerMain.fxml")));
        } catch (IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(loginPage,800,400);
        stage.setScene(scene);
        stage.show();
    }

    private static Scene getScene(Parent parent){return new Scene(parent, 800, 400);}

    public static void setLoginPage(){AppStage.setScene(getScene(loginPage));}
    public static void setClientMain(){AppStage.setScene(getScene(clientMain));}
    public static void setClientBooks(){AppStage.setScene(getScene(clientBooks));}
    public static void setManagerMain(){AppStage.setScene(getScene(managerMain));}
    public static void setAdminMain(){AppStage.setScene(getScene(adminMain));}
}
