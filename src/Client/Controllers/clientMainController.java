package Client.Controllers;

import Client.QueryController;
import Models.Cities;
import Models.Countries;
import Models.Travels;
import Models.TravelsRepr;
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
    private TableView<TravelsRepr> table;

    @FXML
    private TableColumn<TravelsRepr, String> tableCity;

    @FXML
    private TableColumn<TravelsRepr, String> tableCountry;

    @FXML
    private TableColumn<TravelsRepr, String> tableName;

    @FXML
    private TableColumn<TravelsRepr, Integer> tablePrice;

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

    public void updateTravels(TravelsRepr travel){
        Packet packet = new Packet(QueryModel.Travels, QueryMethod.Read,travel);
        System.out.println("Я МЕНЯЮ TRAVELS REPR");
        List<TravelsRepr> models = (List<TravelsRepr>) QueryController.query_request(packet);
        System.out.println("Че пришло " + models);
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
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        tableCity.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

}
