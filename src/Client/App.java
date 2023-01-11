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

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import static Client.SocketClient.log;
import static java.lang.System.exit;

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

    public static void createLogFile() {
        FileHandler fh;
        try {
            File logFile = new File("Client.log");
            if (!logFile.exists()) {
                //System.out.println("Client.log doesn't exist.");
                logFile = new File("./Client.log");
            }
            fh = new FileHandler("Client.log");
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }
        catch (SecurityException | IOException e) { e.printStackTrace(); }
    }


    @Override
    public void start(Stage stage) {
        createLogFile();
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
