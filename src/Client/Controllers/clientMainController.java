package Client.Controllers;

import Client.QueryController;
import Models.Cities;
import Models.Countries;
import Models.Travels;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.List;

public class clientMainController {

    @FXML
    private Button btnBook;

    @FXML
    private Button btnBooks;

    @FXML
    private TableView<Travels> table;

    @FXML
    private TableColumn<Travels, String> tableCity;

    @FXML
    private TableColumn<Travels, String> tableCountry;

    @FXML
    private TableColumn<Travels, String> tableName;

    @FXML
    private TableColumn<Travels, Integer> tablePrice;

    @FXML
    private ComboBox<Integer> tourCity;

    @FXML
    private ComboBox<Integer> tourCountry;

    @FXML
    private ImageView tourImage;

    @FXML
    private ComboBox<Integer> tourType;



    @FXML
    void btnBookClick(ActionEvent event) {

    }

    @FXML
    void btnBooksClick(ActionEvent event) {
        // вызов брони и нужна проверка на выбранный поля в списках


    }

    @FXML
    void countryAction(ActionEvent event) {

    }

    @FXML
    void tableClick(MouseEvent event) {

    }

    @FXML
    void tourTypeAction(ActionEvent event) {

    }

    public void updateTravels(Travels travel){
        Packet packet = new Packet(QueryModel.Travels, QueryMethod.Read,travel);
        System.out.println("Я МЕНЯЮ TRAVELS");
        List<Travels> models = (List<Travels>) QueryController.query_request(packet);
        table.setItems(FXCollections.observableArrayList(models));
    }

    public static clientMainController instance;

    private HashMap<String,Integer> countryToId = new HashMap<>();
    private HashMap<Integer,String> idToCountry = new HashMap<>();

    private HashMap<String,Integer> cityToId = new HashMap<>();
    private HashMap<Integer,String> idToCity = new HashMap<>();

    private HashMap<String,Integer> travelTypeToId = new HashMap<>();
    private HashMap<Integer,String> idToTravelType = new HashMap<>();

    @FXML
    public void initialize(){
        instance = this;
        table.setCellValueFactory(new PropertyValueFactory<>("type"));
        // Нет type
        table.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setCellValueFactory(new PropertyValueFactory<>("city"));
        table.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        table.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        
    }

}
