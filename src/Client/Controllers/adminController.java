package Client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TableColumn<?, ?> citiesCountry;

    @FXML
    private TableColumn<?, ?> citiesId;

    @FXML
    private TableColumn<?, ?> citiesName;

    @FXML
    private ComboBox<?> combo1;

    @FXML
    private ComboBox<?> combo2;

    @FXML
    private ComboBox<?> combo3;

    @FXML
    private ComboBox<?> combo4;

    @FXML
    private ComboBox<?> combo5;

    @FXML
    private ComboBox<?> combo6;

    @FXML
    private TableColumn<?, ?> countriesId;

    @FXML
    private TableColumn<?, ?> countriesName;

    @FXML
    private Tab tabCities;

    @FXML
    private Tab tabCountries;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabTickets;

    @FXML
    private Tab tabTravel;

    @FXML
    private Tab tabUsers;

    @FXML
    private TableView<?> tableCities;

    @FXML
    private TableView<?> tableCountries;

    @FXML
    private TableView<?> tableTickets;

    @FXML
    private TableView<?> tableTravels;

    @FXML
    private TableView<?> tableUsers;

    @FXML
    private TableColumn<?, ?> ticketsId;

    @FXML
    private TableColumn<?, ?> ticketsStatus;

    @FXML
    private TableColumn<?, ?> ticketsTravel;

    @FXML
    private TableColumn<?, ?> ticketsUser;

    @FXML
    private TableColumn<?, ?> travelsCity;

    @FXML
    private TableColumn<?, ?> travelsCoordinates;

    @FXML
    private TableColumn<?, ?> travelsId;

    @FXML
    private TableColumn<?, ?> travelsName;

    @FXML
    private TableColumn<?, ?> travelsPrice;

    @FXML
    private TableColumn<?, ?> travelsUser;

    @FXML
    private TableColumn<?, ?> usersAccessLevel;

    @FXML
    private TableColumn<?, ?> usersHashedPassword;

    @FXML
    private TableColumn<?, ?> usersId;

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



    void disable_buttons(){
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
    }

    void disable_combos(){
        combo1.setDisable(true);
        combo2.setDisable(true);
        combo3.setDisable(true);
        combo4.setDisable(true);
        combo5.setDisable(true);
    }

}
