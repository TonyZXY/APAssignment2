package GUI.CustomerMenu;

import Fileio.DB;
import GUI.Menu.Main;
import MyTi.MyTiSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

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
        }
    }

    @FXML
    private int ticketType() {
        int type;
        if (twoHours.isSelected() && zoneOne.isSelected()) {
            type = 1;
        } else if (twoHours.isSelected() && zoneTwo.isSelected()) {
            type = 3;
        } else if (oneDay.isSelected() && zoneOne.isSelected()) {
            type = 2;
        } else {
            type = 4;
        }
        return type;
    }

    @FXML
    private void purchaseTravelPass() {
        int priceType = ticketType();
        Fileio.DB.purchaseTravelPass(ID,priceType);
//        double amount = MyTiSystem.getPrice(priceType);
//        Calendar date = Calendar.getInstance();
//        char type = Fileio.DB.getUserTypeDB(ID);
//        Fileio.DB.purchase(ID, amount);
//        Fileio.DB.addTravelPassDB(ID, priceType, type, date, amount);
    }

    @FXML
    private void useTravelPass(){
        String from = stationFrom.getValue().toString();
        String to = stationTo.getValue().toString();
        int fromZone = Fileio.DB.getStationZone(from);
        int toZone = Fileio.DB.getStationZone(to);

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
