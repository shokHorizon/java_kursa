package Client.Controllers;

import Client.App;
import Models.*;
import Protocols.Crypto;
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
    private ComboBox<String> combo7;

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
        if (!tableTravels.getSelectionModel().isEmpty() ||
                !tableUsers.getSelectionModel().isEmpty() ||
                !tableCountries.getSelectionModel().isEmpty() ||
                !tableCities.getSelectionModel().isEmpty() ||
                !tableTickets.getSelectionModel().isEmpty()){
            clear_combos();
            tableTravels.getSelectionModel().clearSelection();
            tableUsers.getSelectionModel().clearSelection();
            tableCountries.getSelectionModel().clearSelection();
            tableCities.getSelectionModel().clearSelection();
            tableTickets.getSelectionModel().clearSelection();
           return;
        }


        if (tabTravel.isSelected()) {
            System.out.println("Выбрана трэвэл");
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
                        combo7.getValue(),
                        combo6.getValue(),
                        Integer.parseInt(combo5.getValue()),
                        Integer.parseInt(combo4.getValue()));
                packet.setQueryModel(QueryModel.Travels);
                packet.setModels(travel);
                QueryController.query_request(packet);
                tableTravels.getItems().add(travel);
            }
        }

        else if (tabTickets.isSelected()) {
            System.out.println("Выбрана тикетс");
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), "")) {
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
        }
        else if (tabCities.isSelected()) {
                System.out.println("Выбрана сити");
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

        else if (tabCountries.isSelected()) {
                System.out.println("Выбрана кантри");
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

        else if (tabUsers.isSelected()) {
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), "")) {
                Users users = new Users(
                        Integer.parseInt(combo1.getValue()),
                        combo2.getValue(),
                        Crypto.hashInt(Crypto.hashString(combo3.getValue())),
                        Integer.parseInt(combo4.getValue())
                );
                packet.setQueryModel(QueryModel.Users);
                packet.setModels(users);
                QueryController.query_request(packet);
                tableUsers.getItems().add(users);
            }
        }

    }

    @FXML
    void btnRemoveClick(ActionEvent event) {
        clear_combos();
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
            tableUsers.getItems().remove(users);
        }
        if (!tableCities.getSelectionModel().isEmpty()) {
            Cities cities = tableCities.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Cities,QueryMethod.Delete,cities));
            tableCities.getItems().remove(cities);
        }
        if (!tableCountries.getSelectionModel().isEmpty()) {
            Countries countries = tableCountries.getSelectionModel().getSelectedItem();
            QueryController.query_request(new Packet<>(QueryModel.Countries,QueryMethod.Delete,countries));
            tableCountries.getItems().remove(countries);
        }

    }

    @FXML
    void btnUpdateClick(ActionEvent event) {
        if (!tableTravels.getSelectionModel().isEmpty() &&
                !tableUsers.getSelectionModel().isEmpty() &&
                !tableCountries.getSelectionModel().isEmpty() &&
                !tableCities.getSelectionModel().isEmpty() &&
                !tableTickets.getSelectionModel().isEmpty()) {
            clear_combos();
            tableTravels.getSelectionModel().clearSelection();
            tableUsers.getSelectionModel().clearSelection();
            tableCountries.getSelectionModel().clearSelection();
            tableCities.getSelectionModel().clearSelection();
            tableTickets.getSelectionModel().clearSelection();
               return;
            }

        if (tabTravel.isSelected()) {
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), "") &&
                    !Objects.equals(combo5.getValue(), "") &&
                    !Objects.equals(combo6.getValue(), "") &&
                    !Objects.equals(combo6.getValue(), ""))
            {
                Travels travel = tableTravels.getSelectionModel().getSelectedItem();
                travel.setType(Integer.parseInt(combo1.getValue()));
                travel.setCity(Integer.parseInt(combo2.getValue()));
                travel.setName(combo3.getValue());
                travel.setSupplier(Integer.parseInt(combo4.getValue()));
                travel.setPrice(Integer.parseInt(combo5.getValue()));
                travel.setCoordinates(combo6.getValue());
                travel.setImage(combo7.getValue());
                QueryController.query_request(new Packet<>(QueryModel.Travels,QueryMethod.Update,travel));
                tableTravels.refresh();
            }
        }

        else if (tabTickets.isSelected()) {
            System.out.println("Выбрана тикетс");
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), ""))
            {
                Books books = tableTickets.getSelectionModel().getSelectedItem();
                books.setId(Integer.parseInt(combo1.getValue()));
                books.setTravel(Integer.parseInt(combo2.getValue()));
                books.setUser(Integer.parseInt(combo3.getValue()));
                books.setStatus(Integer.parseInt(combo4.getValue()));
                QueryController.query_request(new Packet<>(QueryModel.Books,QueryMethod.Update,books));
                tableTickets.refresh();
            }
        }

        else if (tabCities.isSelected()) {
            System.out.println("Выбрана сити");
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), ""))
            {
               Cities cities = tableCities.getSelectionModel().getSelectedItem();
                cities.setId(Integer.parseInt(combo1.getValue()));
                cities.setName(combo2.getValue());
                cities.setCountry(Integer.parseInt(combo3.getValue()));
                QueryController.query_request(new Packet<>(QueryModel.Cities,QueryMethod.Update,cities));
                tableCities.refresh();
            }
        }

        else if (tabCountries.isSelected()) {
            System.out.println("Выбрана кантри");
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), ""))
            {
                Countries countries = tableCountries.getSelectionModel().getSelectedItem();
                countries.setId(Integer.parseInt(combo1.getValue()));
                countries.setName(combo2.getValue());
                QueryController.query_request(new Packet<>(QueryModel.Countries,QueryMethod.Update,countries));
                tableCountries.refresh();
            }
        }

        else if (tabUsers.isSelected()) {
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), ""))
            {
                Users users = tableUsers.getSelectionModel().getSelectedItem();
                users.setId(Integer.parseInt(combo1.getValue()));
                users.setLogin(combo2.getValue());
                users.setHashedPassword(Crypto.hashInt(Crypto.hashString(combo3.getValue())));
                users.setAccessLevel(Integer.parseInt(combo4.getValue()));
                QueryController.query_request(new Packet<>(QueryModel.Users,QueryMethod.Update,users));
                tableUsers.refresh();
            }
        }

    }

    @FXML
    void citiesClick(MouseEvent event) {
        if (tableCities.getSelectionModel().isEmpty()){
            clear_combos();
            System.out.println("Ай-ай-ай. Не нажимай на пустые поля");
            return;
        }

        Cities cities = tableCities.getSelectionModel().getSelectedItem();
        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);

        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnUpdate.setDisable(false);

        combo1.setValue(Integer.toString(cities.getId()));
        combo2.setValue(cities.getName());
        combo3.setValue(Integer.toString(cities.getCountry()));

    }

    @FXML
    void citiesTabSelected(Event event) {
        if (tabCities.isSelected())
        {
            clear_combos();
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
    void combo7Action(ActionEvent event) {
    }

    @FXML
    void countriesClick(MouseEvent event) {
        if (tableCountries.getSelectionModel().isEmpty()){
            clear_combos();
            System.out.println("Ай-ай-ай. Не нажимай на пустые поля");
            return;
        }

        Countries countries = tableCountries.getSelectionModel().getSelectedItem();
        combo1.setDisable(false);
        combo2.setDisable(false);

        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnUpdate.setDisable(false);

        combo1.setValue(Integer.toString(countries.getId()));
        combo2.setValue(countries.getName());

    }

    @FXML
    void countriesTabSelected(Event event) {
        if (tabCountries.isSelected())
        {
            clear_combos();
            System.out.println("Switch to countries");
            switchToCountries();
        }
    }

    @FXML
    void ticketsClick(MouseEvent event) {
        if (tableTickets.getSelectionModel().isEmpty()){
            clear_combos();
            System.out.println("Ай-ай-ай. Не нажимай на пустые поля");
            return;
        }

        Books books = tableTickets.getSelectionModel().getSelectedItem();
        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);
        combo4.setDisable(false);

        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnUpdate.setDisable(false);

        combo1.setValue(Integer.toString(books.getId()));
        combo2.setValue(Integer.toString(books.getTravel()));
        combo3.setValue(Integer.toString(books.getUser()));
        combo4.setValue(Integer.toString(books.getStatus()));

    }

    @FXML
    void ticketsTabSelected(Event event) {
        if (tabTickets.isSelected())
        {
            clear_combos();
            System.out.println("Switch to tickets");
            switchToTickets();
        }
    }

    @FXML
    void travelsClick(MouseEvent event) {
        if (tableTravels.getSelectionModel().isEmpty()){
            clear_combos();
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
        combo4.setValue(Integer.toString(travel.getSupplier()));
        combo5.setValue(Integer.toString(travel.getPrice()));
        combo6.setValue(travel.getCoordinates());

    }

    @FXML
    void travelsTabSelected(Event event) {
        if (tabTravel.isSelected() && App.token != 0)
        {
            clear_combos();
            System.out.println("Switch to travels");
            switchToTravels();
        }
    }

    @FXML
    void usersClick(MouseEvent event) {
        clear_combos();
        if (tableUsers.getSelectionModel().isEmpty()){
            System.out.println("Ай-ай-ай. Не нажимай на пустые поля");
            return;
        }
        Users users = tableUsers.getSelectionModel().getSelectedItem();
        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);
        combo4.setDisable(false);

        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnUpdate.setDisable(false);

        combo1.setValue(Integer.toString(users.getId()));
        combo2.setValue(users.getLogin());
        combo3.setValue(Integer.toString(users.getHashedPassword()));
        combo4.setValue(Integer.toString(users.getAccessLevel()));
    }

    @FXML
    void usersTabSelected(Event event) {
        if (tabUsers.isSelected())
        {
            clear_combos();
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
        ticketsTravel.setCellValueFactory(new PropertyValueFactory<>("travel"));
        ticketsUser.setCellValueFactory(new PropertyValueFactory<>("user"));
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
        clear_combos();
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
        clear_combos();
        updateTicketsTable();
        disable_combos();
        clear_prompt_combos();
        btnAdd.setDisable(false);

        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);
        combo4.setDisable(false);

        combo1.setPromptText("Id");
        combo2.setPromptText("Travel");
        combo3.setPromptText("User");
        combo4.setPromptText("Status");
    }

    public void switchToUsers(){
        clear_combos();
        updateUserTable();
        disable_combos();
        clear_prompt_combos();
        btnAdd.setDisable(false);

        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);
        combo4.setDisable(false);

        combo1.setPromptText("id");
        combo2.setPromptText("Login");
        combo3.setPromptText("hashpassword");
        combo4.setPromptText("accessLevel");
    }
    
    public void switchToCities(){
        clear_combos();
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
        clear_combos();
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

    void clear_combos(){
        combo1.setValue("");
        combo2.setValue("");
        combo3.setValue("");
        combo4.setValue("");
        combo5.setValue("");
        combo6.setValue("");
    }

}
