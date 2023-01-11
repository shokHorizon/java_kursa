package Client.Controllers;

import Client.App;
import Client.QueryController;
import Models.*;
import Protocols.Crypto;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.Objects;

public class managerController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

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
    private TabPane tabPane;

    @FXML
    private Tab tabTickets;

    @FXML
    private Tab tabTravels;

    @FXML
    private TableView<Books> tableTickets;

    @FXML
    private TableView<Travels> tableTravels;

    @FXML
    private TableColumn<Books, Integer> ticketsStatus;

    @FXML
    private TableColumn<Books, Integer> ticketsTravel;

    @FXML
    private TableColumn<Books, Integer> ticketsUser;

    @FXML
    private TableColumn<Books, Integer> travelsCity;

    @FXML
    private TableColumn<Travels, String> travelsCoordinates;

    @FXML
    private TableColumn<Travels, String> travelsImage;

    @FXML
    private TableColumn<Travels, String> travelsName;

    @FXML
    private TableColumn<Travels, Integer> travelsPrice;

    @FXML
    private TableColumn<Travels, Integer> travelsType;

    @FXML
    void btnAddClick(ActionEvent event) {
        Packet packet = new Packet<>(null, QueryMethod.Create, null);
        packet.setToken(App.token);
        // Если пустая, то обнуляет
        if (!tableTravels.getSelectionModel().isEmpty() ||
                !tableTickets.getSelectionModel().isEmpty()){
            clear_combos();
            tableTravels.getSelectionModel().clearSelection();
            tableTickets.getSelectionModel().clearSelection();
            return;
        }


        if (tabTravels.isSelected()) {
            //System.out.println("Выбрана трэвэл");
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), "") &&
                    !Objects.equals(combo5.getValue(), "") &&
                    !Objects.equals(combo6.getValue(), "")) {
                Travels travel = new Travels(
                        0,
                        Integer.parseInt(combo1.getValue()),
                        combo2.getValue(),
                        Integer.parseInt(combo3.getValue()),
                        combo4.getValue(),
                        combo5.getValue(),
                        Integer.parseInt(combo6.getValue()),
                        0);
                packet.setQueryModel(QueryModel.Travels);
                packet.setModels(travel);
                QueryController.query_request(packet);
                tableTravels.getItems().add(travel);
            }
        }

        else if (tabTickets.isSelected()) {
            //System.out.println("Выбрана тикетс");
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "")) {
                Books books = new Books(
                        0,
                        Integer.parseInt(combo1.getValue()),
                        Integer.parseInt(combo2.getValue()),
                        Integer.parseInt(combo3.getValue()));
                packet.setQueryModel(QueryModel.Books);
                packet.setModels(books);
                QueryController.query_request(packet);
                tableTickets.getItems().add(books);
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
    }

    @FXML
    void btnUpdateClick(ActionEvent event) {
        if (!tableTravels.getSelectionModel().isEmpty() &&
                !tableTickets.getSelectionModel().isEmpty()) {
            clear_combos();
            tableTravels.getSelectionModel().clearSelection();
            tableTickets.getSelectionModel().clearSelection();
            return;
        }

        if (tabTravels.isSelected()) {
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), "") &&
                    !Objects.equals(combo4.getValue(), "") &&
                    !Objects.equals(combo5.getValue(), "") &&
                    !Objects.equals(combo6.getValue(), ""))
            {
                Travels travel = tableTravels.getSelectionModel().getSelectedItem();
                travel.setType(Integer.parseInt(combo1.getValue()));
                travel.setName(combo2.getValue());
                travel.setCity(Integer.parseInt(combo3.getValue()));
                travel.setImage(combo4.getValue());
                travel.setCoordinates(combo5.getValue());
                travel.setPrice(Integer.parseInt(combo6.getValue()));
                QueryController.query_request(new Packet<>(QueryModel.Travels,QueryMethod.Update,travel));
                tableTravels.refresh();
            }
        }

        else if (tabTickets.isSelected()) {
            //System.out.println("Выбрана тикетс");
            if (!Objects.equals(combo1.getValue(), "") &&
                    !Objects.equals(combo2.getValue(), "") &&
                    !Objects.equals(combo3.getValue(), ""))
            {
                Books books = tableTickets.getSelectionModel().getSelectedItem();
                books.setTravel(Integer.parseInt(combo1.getValue()));
                books.setUser(Integer.parseInt(combo2.getValue()));
                books.setStatus(Integer.parseInt(combo3.getValue()));
                QueryController.query_request(new Packet<>(QueryModel.Books,QueryMethod.Update,books));
                tableTickets.refresh();
            }
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
    void ticketsClicked(MouseEvent event) {
        if (tableTickets.getSelectionModel().isEmpty()){
            clear_combos();
            //System.out.println("Ай-ай-ай. Не нажимай на пустые поля");
            return;
        }

        Books books = tableTickets.getSelectionModel().getSelectedItem();
        combo1.setDisable(false);
        combo2.setDisable(false);
        combo3.setDisable(false);

        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnUpdate.setDisable(false);

        combo1.setValue(Integer.toString(books.getTravel()));
        combo2.setValue(Integer.toString(books.getUser()));
        combo3.setValue(Integer.toString(books.getStatus()));
    }

    @FXML
    void ticketsTabSelected(Event event) {
        if (tabTickets.isSelected())
        {
            clear_combos();
            //System.out.println("Switch to tickets");
            switchToTickets();
        }
    }

    @FXML
    void travelsClicked(MouseEvent event) {
        if (tableTravels.getSelectionModel().isEmpty()){
            clear_combos();
            //System.out.println("Ай-ай-ай. Не нажимай на пустые поля");
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
        combo2.setValue(travel.getName());
        combo3.setValue(Integer.toString(travel.getCity()));
        combo4.setValue(travel.getImage());
        combo5.setValue(travel.getCoordinates());
        combo6.setValue(Integer.toString(travel.getPrice()));
    }



    @FXML
    void travelsTabSelected(Event event) {
        if (tabTravels.isSelected() && App.token != 0)
        {
            clear_combos();
            //System.out.println("Switch to travels");
            switchToTravels();
        }
    }

    public static managerController instance;

    @FXML
    public void initialize(){
        disable_buttons();
        disable_combos();
        instance = this;

        travelsType.setCellValueFactory(new PropertyValueFactory<>("type"));
        travelsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        travelsCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        travelsImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        travelsCoordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        travelsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        ticketsTravel.setCellValueFactory(new PropertyValueFactory<>("travel"));
        ticketsUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        ticketsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

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
        combo2.setPromptText("Name");
        combo3.setPromptText("City");
        combo4.setPromptText("Image");
        combo5.setPromptText("Coordinates");
        combo6.setPromptText("Price");
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

        combo1.setPromptText("Travel");
        combo2.setPromptText("User");
        combo3.setPromptText("Status");
    }

    void updateTravelsTable(){
        Packet packet = new Packet(QueryModel.Travels, QueryMethod.Read,null);
        //System.out.println("Я МЕНЯЮ TRAVELS");
        List<Travels> models = (List<Travels>) QueryController.query_request(packet);
        tableTravels.setItems(FXCollections.observableArrayList(models));
    }

    void updateTicketsTable(){
        //System.out.println("Я МЕНЯЮ TICKETS");
        Packet packet = new Packet(QueryModel.Books, QueryMethod.Read,null);
        List <Books> models = (List<Books>) QueryController.query_request(packet);
        tableTickets.setItems(FXCollections.observableArrayList(models));
    }



}
