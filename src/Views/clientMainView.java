package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class clientMainView {

    @FXML
    private Button btnBook;

    @FXML
    private Button btnBooks;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> tableCity;

    @FXML
    private TableColumn<?, ?> tableCountry;

    @FXML
    private TableColumn<?, ?> tableName;

    @FXML
    private TableColumn<?, ?> tablePrice;

    @FXML
    private ComboBox<?> tourCity;

    @FXML
    private ComboBox<?> tourCountry;

    @FXML
    private ImageView tourImage;

    @FXML
    private ComboBox<?> tourType;

    @FXML
    void btnBookClick(ActionEvent event) {

    }

    @FXML
    void btnBooksClick(ActionEvent event) {

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

}
