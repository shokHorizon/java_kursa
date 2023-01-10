package Client.Controllers;

import Client.App;
import Client.QueryController;
import Models.*;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;
import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.*;

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
    private ComboBox<String> tourCity;

    @FXML
    private ComboBox<String> tourCountry;

    @FXML
    private ImageView tourImage;

    @FXML
    private ComboBox<String> tourType;



    @FXML
    void btnBookClick(ActionEvent event) {
        if (!table.getSelectionModel().isEmpty())
        {
            Books books = new Books(0,table.getSelectionModel().getSelectedItem().getId(),0,0);
            QueryController.query_request(new Packet<>(QueryModel.Books,QueryMethod.Create,books));
        }
    }

    @FXML
    void btnBooksClick(ActionEvent event) {
        // вызов брони и нужна проверка на выбранный поля в списках
        App.setClientBooks();

    }

    @FXML
    void countryAction(ActionEvent event) {
        if (Objects.equals(tourCountry.getValue(), "")) {
            tourCountry.setPromptText("Страна");
            System.out.println(tourCountry.getPromptText() + " аааааааааааааааааааааааааааааааа");
        }
        comboUpdate();
    }

    @FXML
    void cityAction(ActionEvent event) {
        comboUpdate();
    }

    @FXML
    void tourTypeAction(ActionEvent event) {
        comboUpdate();
    }

    @FXML
    void tableClick(MouseEvent event) {
        if (!table.getSelectionModel().isEmpty())
        {
            try {
                tourImage.setImage(new Image(table.getSelectionModel().getSelectedItem().getImage()));
            } catch (IllegalArgumentException e)
            {
                tourImage.setImage(null);
                System.out.println("Корявая ссылка");
            }
        }
    }
    private boolean isUpdating = false;

    public void updateTravels(TravelsRepr travel){
        isUpdating = true;
        Packet packet = new Packet(QueryModel.Travels, QueryMethod.Read,travel);
        System.out.println("Я МЕНЯЮ TRAVELS REPR");
        List<TravelsRepr> models = (List<TravelsRepr>) QueryController.query_request(packet);
        System.out.println("Че пришло " + models);
        table.setItems(FXCollections.observableArrayList(models));
        updateCombos(models);
        isUpdating = false;
    }

    public static clientMainController instance;

    public void updateCombos(List<TravelsRepr> travels){
       HashSet<String> typesLst = new HashSet<>();
       HashSet<String> countriesLst = new HashSet<>();
       HashSet<String> citiesLst = new HashSet<>();

        typesLst.add("");
        countriesLst.add("");
        citiesLst.add("");
        for (TravelsRepr repr: travels)
        {
            typesLst.add(repr.getType());
            countriesLst.add(repr.getCountry());
            citiesLst.add(repr.getCity());
        }

        tourType.setItems(FXCollections.observableArrayList(typesLst));
        tourCity.setItems(FXCollections.observableArrayList(citiesLst));
        tourCountry.setItems(FXCollections.observableArrayList(countriesLst));
    }

    private void comboUpdate(){
        if (!isUpdating) {
            TravelsRepr travelsRepr = new TravelsRepr(0,null,null,null,null,0,null);
            if (!tourType.getSelectionModel().isEmpty())
                travelsRepr.setType(tourType.getValue());
            if (!tourCity.getSelectionModel().isEmpty())
                travelsRepr.setCity(tourCity.getValue());
            if (!tourCountry.getSelectionModel().isEmpty())
                travelsRepr.setCountry(tourCountry.getValue());
            updateTravels(travelsRepr);
        }
    }

    @FXML
    public void initialize(){
        instance = this;
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        tableCity.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

}
