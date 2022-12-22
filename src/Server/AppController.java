package Server;

import MVC.Models.TicketsModel;
import MVC.Models.ToursModel;
import MVC.Models.TripsModel;
import Server.Tickets.TicketsController;
import Server.Tours.ToursController;
import Server.Trips.TripsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Map;

public class AppController {
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_enter;

    @FXML
    private TextField numField;

    @FXML
    private TextField textField;

    @FXML
    private TableColumn<TicketsModel, String> ticketId;

    @FXML
    private TableColumn<TicketsModel, String> ticketName;

    @FXML
    private TableColumn<TicketsModel, String> ticketTrip;

    @FXML
    private TableView<TicketsModel> ticketsTable;

    @FXML
    private TableColumn<ToursModel, String> toursCity;

    @FXML
    private TableColumn<ToursModel, String> toursId;

    @FXML
    private TableColumn<ToursModel, String> toursPrice;

    @FXML
    private TableView<ToursModel> toursTable;

    @FXML
    private TableColumn<TripsModel, String> tripsDatetime;

    @FXML
    private TableColumn<TripsModel, String> tripsId;

    @FXML
    private TableColumn<TripsModel, String> tripsPrice;

    @FXML
    private TableView<TripsModel> tripsTable;

    @FXML
    private TableColumn<TripsModel, String> tripsTour;

    @FXML
    public void initialize() {
        toursId.setCellValueFactory(new PropertyValueFactory<>("id"));
        toursCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        toursPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tripsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tripsTour.setCellValueFactory(new PropertyValueFactory<>("tour"));
        tripsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tripsDatetime.setCellValueFactory(new PropertyValueFactory<>("datetime"));

        ticketId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketTrip.setCellValueFactory(new PropertyValueFactory<>("trip"));
        ticketName.setCellValueFactory(new PropertyValueFactory<>("name"));

        switchToTours();
    }

    private void deselectEverything(){
        toursTable.getSelectionModel().select(-1);
        ticketsTable.getSelectionModel().select(-1);
        tripsTable.getSelectionModel().select(-1);
    }

    public void disableButtons(){
        btn_add.setDisable(true);
        btn_back.setDisable(true);
        btn_delete.setDisable(true);
        btn_enter.setDisable(true);
    }

    public void zeroTextFields(){
        textField.setText("");
        numField.setText("");
        textField.setPromptText("");
        numField.setPromptText("");
        textField.setDisable(true);
        textField.setDisable(true);
    }

    public void switchToTours(){
        disableButtons();
        zeroTextFields();
        deselectEverything();

        tripsTable.setVisible(false);
        ticketsTable.setVisible(false);
        toursTable.setVisible(true);

        btn_add.setDisable(false);
        textField.setDisable(false);
        textField.setPromptText("City");

        // Getting data
        updateToursTable();
    }

    public void switchToTrips(){
        disableButtons();
        zeroTextFields();
        deselectEverything();

        tripsTable.setVisible(true);
        ticketsTable.setVisible(false);
        toursTable.setVisible(false);

        btn_add.setDisable(false);
        btn_back.setDisable(true);

        textField.setPromptText("dd.mm.yy hh:mm");
        numField.setPromptText("price");

    }

    public void switchToTickets(){
        disableButtons();
        deselectEverything();

        tripsTable.setVisible(false);
        ticketsTable.setVisible(true);
        toursTable.setVisible(false);

        btn_add.setDisable(false);
        btn_back.setDisable(false);
        textField.setDisable(false);
        textField.setPromptText("Name");
    }

    public void tableMouseClickHandler(){
        if (toursTable.isVisible() && !toursTable.getSelectionModel().isEmpty()){
            ToursModel model = toursTable.getSelectionModel().getSelectedItem();
            textField.setText(model.getCity());
            btn_delete.setDisable(false);
            btn_enter.setDisable(false);
            return;
        }
        if (tripsTable.isVisible() && !tripsTable.getSelectionModel().isEmpty()){
            TripsModel model = tripsTable.getSelectionModel().getSelectedItem();
            textField.setText(model.getDate().toString());
            numField.setText(String.valueOf(model.getPrice()));
            btn_delete.setDisable(false);
            btn_enter.setDisable(false);
            return;
        }
        if (ticketsTable.isVisible() && !ticketsTable.getSelectionModel().isEmpty()){
            TicketsModel model = ticketsTable.getSelectionModel().getSelectedItem();
            textField.setText(model.getName());
            btn_delete.setDisable(false);
        }
    }

    private void updateToursTable(){
        List<ToursModel> toursModel = ToursController.INSTANCE.getTours();
        toursTable.setItems(FXCollections.observableArrayList(toursModel));
    }

    private void updateTripsTable(){
        List<TripsModel> tripsModels = TripsController.INSTANCE.getTrips();
        tripsTable.setItems(FXCollections.observableArrayList(tripsModels));
    }

    private void updateTicketsTable(){
        List<TicketsModel> ticketsModels = TicketsController.INSTANCE.getTickets();
        ticketsTable.setItems(FXCollections.observableArrayList(ticketsModels));
    }

    public void btnRemoveHandler(){
        if (toursTable.isVisible() && !toursTable.getSelectionModel().isEmpty()){
            ToursModel model = toursTable.getSelectionModel().getSelectedItem();
            ToursController.INSTANCE.deleteTour(model.getId());
            updateToursTable();
            btn_delete.setDisable(false);
            btn_enter.setDisable(false);
            return;
        }
    }

}
