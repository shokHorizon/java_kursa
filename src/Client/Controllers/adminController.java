package Client.Controllers;

import Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class adminController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Cities, Integer> citiesCountry;

    @FXML
    private TableColumn<Cities, Integer> citiesId;

    @FXML
    private TableColumn<Cities, String> citiesName;

    @FXML
    private ComboBox<String> combo1;

    @FXML
    private ComboBox<String> combo2;

    @FXML
    private ComboBox<String> combo3;

    @FXML
    private ComboBox<String> combo4;

    @FXML
    private ComboBox<String> combo5;

    @FXML
    private ComboBox<String> combo6;

    @FXML
    private TableColumn<Countries, Integer> countriesId;

    @FXML
    private TableColumn<Countries, String> countriesName;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Cities> tableCities;

    @FXML
    private TableView<Countries> tableCountries;

    @FXML
    private TableView<Books> tableTickets;

    @FXML
    private TableView<Travels> tableTravels;

    @FXML
    private TableView<Users> tableUsers;

    @FXML
    private TableColumn<Books, Integer> ticketsId;

    @FXML
    private TableColumn<Books, Integer> ticketsStatus;

    @FXML
    private TableColumn<Books, Integer> ticketsTravel;

    @FXML
    private TableColumn<Books, Integer> ticketsUser;

    @FXML
    private TableColumn<Travels, Integer> travelsCity;

    @FXML
    private TableColumn<Travels, String> travelsCoordinates;

    @FXML
    private TableColumn<Travels, Integer> travelsId;

    @FXML
    private TableColumn<Travels, String> travelsName;

    @FXML
    private TableColumn<Travels, Integer> travelsPrice;

    @FXML
    private TableColumn<Travels, Integer> travelsUser;

    @FXML
    private TableColumn<Users, Integer> usersAccessLevel;

    @FXML
    private TableColumn<Users, Integer> usersHashedPassword;

    @FXML
    private TableColumn<Users, Integer> usersId;

    @FXML
    private VBox vbox;

    @FXML
    void btnAddClick(ActionEvent event) {

    }

    @FXML
    void btnRemoveClick(ActionEvent event) {

    }

    @FXML
    void btnUpdateClick(ActionEvent event) {

    }

    @FXML
    void citiesClick(MouseEvent event) {

    }

    @FXML
    void citiesTabSelected(ActionEvent event) {

    }

    @FXML
    void combo1Action(ActionEvent event) {

    }

    @FXML
    void combo2Action(ActionEvent event) {

    }

    @FXML
    void combo3Action(ActionEvent event) {

    }

    @FXML
    void combo4Action(ActionEvent event) {

    }

    @FXML
    void combo5Action(ActionEvent event) {

    }

    @FXML
    void combo6Action(ActionEvent event) {

    }

    @FXML
    void countriesClick(MouseEvent event) {

    }

    @FXML
    void countriesTabSelected(ActionEvent event) {

    }

    @FXML
    void ticketsClick(MouseEvent event) {

    }

    @FXML
    void ticketsTabSelected(ActionEvent event) {

    }

    @FXML
    void travelsClick(MouseEvent event) {

    }

    @FXML
    void travelsTabSelected(ActionEvent event) {

    }

    @FXML
    void usersClick(MouseEvent event) {

    }

    @FXML
    void usersTabSelected(ActionEvent event) {

    }

}
