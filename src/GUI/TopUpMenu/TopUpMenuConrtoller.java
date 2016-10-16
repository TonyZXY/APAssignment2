package GUI.TopUpMenu;

import Fileio.DB.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Project APAssignment2
 * Created by TonyZheng on 5/10/16.
 */
public class TopUpMenuConrtoller {

    private double balance;
    private String id;

    @FXML
    private TextField IDField;

    @FXML
    private TextField amountField;

    @FXML
    private Label balanceLabel;


    @FXML
    private void IDFieldEvent() {
        id = IDField.getText();
    }


    @FXML
    private double checkUsersBalance() {
        double getBalance = Fileio.DB.getUserBalanceDB(id);
        return getBalance;
    }

    @FXML
    private void showBalance() {
        IDFieldEvent();
        balance = checkUsersBalance();
        if (balance == -1) {
            balanceLabel.setText("User Not found!");
        } else {
            balanceLabel.setText("$" + balance);
        }
    }

    @FXML
    private double setAmountField() {
        double amount = Double.parseDouble(amountField.getText());
        return amount;
    }

    @FXML
    private void topUP() {
        double amount = setAmountField();
        double cridit = amount + balance;
        Fileio.DB.topUp(id, cridit);
    }

    @FXML
    private void cancel() throws IOException {
        IDField.clear();
        IDFieldEvent();
        checkUsersBalance();
        GUI.Menu.Main.showMainCenter();
    }
}
