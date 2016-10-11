package GUI.CustomerMenu;

import Fileio.DB;
import GUI.Menu.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Project APAssignment2
 * Created by TonyZheng on 3/10/16.
 */
public class CustomerMenuController {

    ObservableList<String> dayChioceBoxList = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    ObservableList<String> stations = FXCollections.observableArrayList();

    public void getStations() {
        ArrayList st = DB.getStation();
        for (int i = 0; i < st.size(); i++) {
            stations.add((String) st.get(i));
        }
    }

    @FXML
    private Label balanceLabel;

    private double balance;
    private double cridit;

    @FXML
    private TextField IDField;
    private String ID;


    @FXML
    private ChoiceBox dayChioceBox;

    @FXML
    private RadioButton twoHours;
    @FXML
    private RadioButton oneDay;
    @FXML
    private RadioButton zoneOne;
    @FXML
    private RadioButton zoneTwo;

    @FXML
    private ComboBox stationFrom;

    @FXML
    private ComboBox stationTo;

    @FXML
    private void IDFieldEvent() {
        ID = IDField.getText();
    }

    @FXML
    private void cuntinue() {
        IDFieldEvent();
        balance = Fileio.DB.getUserBalanceDB(ID);
        showBalance();
    }

    @FXML
    private void showBalance() {
        if (balance == -1) {
            balanceLabel.setText("User Not found!");
        } else {
            balanceLabel.setText("$" + balance);
            cridit = balance;
        }
    }



    @FXML
    private void initialize() {
        getStations();

        stationFrom.setItems(stations);

        stationTo.setItems(stations);

        dayChioceBox.setValue("Monday");
        dayChioceBox.setItems(dayChioceBoxList);
    }


    @FXML
    private void changeToTopUp() throws IOException {
        Main.showTopUpMenuScene();
    }//我是宝宝，我想回家吃蛋糕,小兔子乖乖，把腿掰开，我要进来，不掰不掰我不掰，套套都不带，叔叔你真坏
}
