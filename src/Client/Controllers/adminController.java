package Client.Controllers;

import Models.*;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;
import Server.QueryController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Objects;

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
        if (!tableTravels.getSelectionModel().isEmpty()) {
           tableTravels.getSelectionModel().clearSelection();
            combo1.setValue("");
            combo2.setValue("");
            combo3.setValue("");
            combo4.setValue("");
            combo5.setValue("");
            combo6.setValue("");
           return;
        }
        if (!Objects.equals(combo1.getValue(), "") &&
            !Objects.equals(combo2.getValue(), "") &&
            !Objects.equals(combo3.getValue(), "") &&
            !Objects.equals(combo4.getValue(), "") &&
            !Objects.equals(combo5.getValue(), "") &&
            !Objects.equals(combo6.getValue(), ""))
        {
            Travels travel = new Travels (
                0,
                 Integer.parseInt(combo1.getValue()),
                 combo3.getValue(),
                 Integer.parseInt(combo2.getValue()),
                 "Фото голого Ельцина",
                 combo6.getValue(),
                 Integer.parseInt(combo5.getValue()),
                 combo4.getValue());
            QueryController.query_request(new Packet<>(QueryModel.Travels,QueryMethod.Create,travel));
            tableTravels.getItems().add(travel);
        }


    }

    @FXML
    void btnRemoveClick(ActionEvent event) {
        if (!tableTravels.getSelectionModel().isEmpty()) {
            Travels travel = tableTravels.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Travels,QueryMethod.Delete,travel));
            tableTravels.getItems().remove(travel);
        }
    }

    @FXML
    void btnUpdateClick(ActionEvent event) {
        if (tableTravels.getSelectionModel().isEmpty()) {
               tableTravels.getSelectionModel().clearSelection();
                combo1.setValue("");
                combo2.setValue("");
                combo3.setValue("");
                combo4.setValue("");
                combo5.setValue("");
                combo6.setValue("");
               return;
            }
            if (!Objects.equals(combo1.getValue(), "") &&
                !Objects.equals(combo2.getValue(), "") &&
                !Objects.equals(combo3.getValue(), "") &&
                !Objects.equals(combo4.getValue(), "") &&
                !Objects.equals(combo5.getValue(), "") &&
                !Objects.equals(combo6.getValue(), ""))
            {
                Travels travel = tableTravels.getSelectionModel().getSelectedItem();
                travel.setType(Integer.parseInt(combo1.getValue()));
                travel.setCity(Integer.parseInt(combo2.getValue()));
                travel.setName(combo3.getValue());
                travel.setSupplier(combo4.getValue());
                travel.setPrice(Integer.parseInt(combo5.getValue()));
                travel.setCoordinates(combo6.getValue());
                QueryController.query_request(new Packet<>(QueryModel.Travels,QueryMethod.Update,travel));
                tableTravels.refresh();
            }
    }

    @FXML
    void citiesClick(MouseEvent event) {

    }

    @FXML
    void citiesTabSelected(Event event) {
        if (tabCities.isSelected())
        {
            System.out.println("Switch to cities");
            switchToCities();
        }
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
    void countriesTabSelected(Event event) {
        if (tabCountries.isSelected())
        {
            System.out.println("Switch to countries");
            switchToCountries();
        }
    }

    @FXML
    void ticketsClick(MouseEvent event) {

    }

    @FXML
    void ticketsTabSelected(Event event) {
        if (tabTickets.isSelected())
        {
            System.out.println("Switch to tickets");
            switchToTickets();
        }
    }

    @FXML
    void travelsClick(MouseEvent event) {
        if (tableTravels.getSelectionModel().isEmpty()){
            System.out.println("Ай-ай-ай. Не нажимай на пустые поля");
            return;
        }

        Travels travel = tableTravels.getSelectionModel().getSelectedItem();
        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);
        combo4.setDisable(false);
        combo5.setDisable(false);
        combo6.setDisable(false);

        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnUpdate.setDisable(false);
        
        combo1.setValue(Integer.toString(travel.getType()));
        combo2.setValue(Integer.toString(travel.getCity()));
        combo3.setValue(travel.getName());
        combo4.setValue(travel.getSupplier());
        combo5.setValue(Integer.toString(travel.getPrice()));
        combo6.setValue(travel.getCoordinates());

    }

    @FXML
    void travelsTabSelected(Event event) {
        if (tabTravel.isSelected())
        {
            System.out.println("Switch to travels");
            switchToTravels();
        }
    }

    @FXML
    void usersClick(MouseEvent event) {

    }

    @FXML
    void usersTabSelected(Event event) {
        if (tabUsers.isSelected())
        {
            System.out.println("Switch to users");
            switchToUsers();
        }
    }

    public static adminController instance;

    @FXML
    public void initialize(){
        disable_buttons();
        disable_combos();
        instance = this;
        travelsId.setCellValueFactory(new PropertyValueFactory<>("type"));
        // Нет type
        travelsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        travelsCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        travelsCoordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        travelsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        travelsUser.setCellValueFactory(new PropertyValueFactory<>("supplier"));

    }

    public void switchToTravels(){
        updateTravelsTable();
        disable_combos();
        btnAdd.setDisable(false);

        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);
        combo4.setDisable(false);
        combo5.setDisable(false);
        combo6.setDisable(false);
        
        combo1.setPromptText("Type");
        combo2.setPromptText("City");
        combo3.setPromptText("Name");
        combo4.setPromptText("Supplier");
        combo5.setPromptText("Price");
        combo6.setPromptText("Coordinates");
    }
    
    public void switchToTickets(){
        updateTicketsTable();
        disable_combos();
        clear_prompt_combos();
        btnAdd.setDisable(false);

        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);
        
        combo1.setPromptText("Type");
        combo2.setPromptText("City");
        combo3.setPromptText("Name");
    }

    public void switchToUsers(){
            updateUserTable();
            disable_combos();
            clear_prompt_combos();
            btnAdd.setDisable(false);
    
            combo1.setDisable(false);
            combo2.setDisable(false);
            combo3.setDisable(false);
            combo1.setPromptText("Login");
            combo2.setPromptText("hashpassword");
            combo3.setPromptText("accessLevel");
    }
    
    public void switchToCities(){
        updateCitiesTable();
        disable_combos();
        clear_prompt_combos();
        btnAdd.setDisable(false);

        combo1.setDisable(false);
        combo2.setDisable(false);

        combo1.setPromptText("Name");
        combo2.setPromptText("Country");
    }
    
    public void switchToCountries(){
        updateCountriesTable();
        disable_combos();
        clear_prompt_combos();
        btnAdd.setDisable(false);

        combo1.setDisable(false);
        
        combo1.setPromptText("Name");

    }

    void updateTravelsTable(){
        Packet packet = new Packet(QueryModel.Travels, QueryMethod.Read,null);
        System.out.println("Я МЕНЯЮ TRAVELS");
        LinkedList <Travels> models = new LinkedList<>(QueryController.query_request(packet).getModels());
        tableTravels.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateTicketsTable(){
        System.out.println("Я МЕНЯЮ TICKETS");
        Packet packet = new Packet(QueryModel.Books, QueryMethod.Read,null);
        LinkedList <Books> models = new LinkedList<>(QueryController.query_request(packet).getModels());
        tableTickets.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateUserTable(){
        System.out.println("Я МЕНЯЮ USER");
        Packet packet = new Packet(QueryModel.Users, QueryMethod.Read,null);
        LinkedList <Users> models = new LinkedList<>(QueryController.query_request(packet).getModels());
        tableUsers.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateCitiesTable(){
        System.out.println("Я МЕНЯЮ CITIES");
        Packet packet = new Packet(QueryModel.Cities, QueryMethod.Read,null);
        LinkedList <Cities> models = new LinkedList<>(QueryController.query_request(packet).getModels());
        tableCities.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateCountriesTable(){
        System.out.println("Я МЕНЯЮ COUNTRIES");
        Packet packet = new Packet(QueryModel.Countries, QueryMethod.Read,null);
        LinkedList <Countries> models = new LinkedList<>(QueryController.query_request(packet).getModels());
        tableCountries.setItems(FXCollections.observableArrayList(models));
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
        combo6.setDisable(true);
    }

    void clear_prompt_combos(){
        combo1.setPromptText("");
        combo2.setPromptText("");
        combo3.setPromptText("");
        combo4.setPromptText("");
        combo5.setPromptText("");
        combo6.setPromptText("");
    }

}
