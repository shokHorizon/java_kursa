package Client.Controllers;

import Client.App;
import Client.QueryController;
import Models.Books;
import Models.Travels;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class clientBooksController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRemove;

    @FXML
    private ListView<Books> tourList;

    @FXML
    void btnBackClick(Event event) {
        App.setClientMain(); // ?
    }

    @FXML
    void btnRemoveClick(Event event) {

    }

    @FXML
    void tourListClick(MouseEvent event) {

    }

    public static clientBooksController instance;

    @FXML
    public void initialize(){
        instance = this;

//        Packet packet = new Packet(QueryModel.Books, QueryMethod.Read,null);
//        System.out.println("Я МЕНЯЮ TRAVELS");
//        List<Books> models = (List<Books>) QueryController.query_request(packet);
//        tourList.setItems(FXCollections.observableArrayList(models));
    }

}
