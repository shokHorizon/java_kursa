package Client.Controllers;

import Client.App;
import Models.*;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;
import Server.AccessManager;
import Client.QueryController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;
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
    private TableColumn<Users, String> usersLogin;

    @FXML
    private VBox vbox;

    @FXML
    void btnAddClick(ActionEvent event) {

        Packet packet = new Packet<>(null, QueryMethod.Create, null);
        packet.setToken(App.token);
        // Если пустая, то обнуляет
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


        if (tabTravel.isSelected()) {
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), "") &&
                    !Objects.equals(combo5.getValue(), "") &&
                    !Objects.equals(combo6.getValue(), "")) {
                Travels travel = new Travels(
                        0,
                        Integer.parseInt(combo1.getValue()),
                        combo3.getValue(),
                        Integer.parseInt(combo2.getValue()),
                        "Фото голого Ельцина",
                        combo6.getValue(),
                        Integer.parseInt(combo5.getValue()),
                        combo4.getValue());
                packet.setQueryModel(QueryModel.Travels);
                packet.setModels(travel);
                QueryController.query_request(packet);
                tableTravels.getItems().add(travel);
            }
        }

        if (tabTickets.isSelected()) {
            if      (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), "") ) {
                Books books = new Books(
                        Integer.parseInt(combo1.getValue()),
                        Integer.parseInt(combo2.getValue()),
                        Integer.parseInt(combo3.getValue()),
                        Integer.parseInt(combo4.getValue()));
                packet.setQueryModel(QueryModel.Books);
                packet.setModels(books);
                QueryController.query_request(packet);
                tableTickets.getItems().add(books);
            }
            if (tabCities.isSelected()) {
                if (!Objects.equals(combo1.getValue(), "") &&
                        !Objects.equals(combo2.getValue(), "") &&
                        !Objects.equals(combo3.getValue(), "")) {
                    Cities cities = new Cities(
                            Integer.parseInt(combo1.getValue()),
                            combo2.getValue(),
                            Integer.parseInt(combo3.getValue()));
                    packet.setQueryModel(QueryModel.Cities);
                    packet.setModels(cities);
                    QueryController.query_request(packet);
                    tableCities.getItems().add(cities);
                }
            }

            if (tabCountries.isSelected()) {
                if (!Objects.equals(combo1.getValue(), "") &&
                        !Objects.equals(combo2.getValue(), "")) {
                    Countries countries = new Countries(
                            Integer.parseInt(combo1.getValue()),
                            combo2.getValue());
                    packet.setQueryModel(QueryModel.Countries);
                    packet.setModels(countries);
                    QueryController.query_request(packet);
                    tableCountries.getItems().add(countries);
                }
            }
        }

    }

    @FXML
    void btnRemoveClick(ActionEvent event) {
        if (!tableTravels.getSelectionModel().isEmpty()) {
            Travels travel = tableTravels.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Travels,QueryMethod.Delete,travel));
            tableTravels.getItems().remove(travel);
        }
        if (!tableTickets.getSelectionModel().isEmpty()) {
            Books books = tableTickets.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Books,QueryMethod.Delete,books));
            tableTickets.getItems().remove(books);
        }
        if (!tableUsers.getSelectionModel().isEmpty()) {
            Users users = tableUsers.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Users,QueryMethod.Delete,users));
            tableTravels.getItems().remove(users);
        }
        if (!tableCities.getSelectionModel().isEmpty()) {
            Cities cities = tableCities.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Cities,QueryMethod.Delete,cities));
            tableTravels.getItems().remove(cities);
        }
        if (!tableCountries.getSelectionModel().isEmpty()) {
            Countries countries = tableCountries.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Countries,QueryMethod.Delete,countries));
            tableTravels.getItems().remove(countries);
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

        ticketsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketsUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        ticketsTravel.setCellValueFactory(new PropertyValueFactory<>("travel"));
        ticketsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        usersId.setCellValueFactory(new PropertyValueFactory<>("id"));
        usersLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        usersHashedPassword.setCellValueFactory(new PropertyValueFactory<>("hashedPassword"));
        usersAccessLevel.setCellValueFactory(new PropertyValueFactory<>("accessLevel"));

        citiesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        citiesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        citiesCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        countriesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        countriesName.setCellValueFactory(new PropertyValueFactory<>("name"));


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
        combo4.setDisable(false);

        combo1.setPromptText("Id");
        combo2.setPromptText("User");
        combo3.setPromptText("Travel");
        combo4.setPromptText("Status");
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
        combo3.setDisable(false);


        combo1.setPromptText("Id");
        combo2.setPromptText("Name");
        combo3.setPromptText("Country");
    }
    
    public void switchToCountries(){
        updateCountriesTable();
        disable_combos();
        clear_prompt_combos();
        btnAdd.setDisable(false);

        combo1.setDisable(false);
        combo2.setDisable(false);

        combo1.setPromptText("Id");
        combo2.setPromptText("Name");

    }

    void updateTravelsTable(){
        Packet packet = new Packet(QueryModel.Travels, QueryMethod.Read,null);
        System.out.println("Я МЕНЯЮ TRAVELS");
        List<Travels> models = (List<Travels>) QueryController.query_request(packet);
        tableTravels.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateTicketsTable(){
        System.out.println("Я МЕНЯЮ TICKETS");
        Packet packet = new Packet(QueryModel.Books, QueryMethod.Read,null);
        List <Books> models = (List<Books>) QueryController.query_request(packet);
        tableTickets.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateUserTable(){
        System.out.println("Я МЕНЯЮ USER");
        Packet packet = new Packet(QueryModel.Users, QueryMethod.Read,null);
        List <Users> models = (List<Users>) QueryController.query_request(packet);
        tableUsers.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateCitiesTable(){
        System.out.println("Я МЕНЯЮ CITIES");
        Packet packet = new Packet(QueryModel.Cities, QueryMethod.Read,null);
        List <Cities> models = (List<Cities>) QueryController.query_request(packet);
        tableCities.setItems(FXCollections.observableArrayList(models));
    }
    
    void updateCountriesTable(){
        System.out.println("Я МЕНЯЮ COUNTRIES");
        Packet packet = new Packet(QueryModel.Countries, QueryMethod.Read,null);
        List <Countries> models = (List<Countries>) QueryController.query_request(packet);
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
