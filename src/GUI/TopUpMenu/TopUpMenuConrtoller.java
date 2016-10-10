package GUI.TopUpMenu;

import Fileio.DB.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Project APAssignment2
 * Created by TonyZheng on 5/10/16.
 */
public class TopUpMenuConrtoller {

    private double cridit;
    private double balance;
    private String id;

    @FXML
    private TextField IDField;

    @FXML
    private TextField amountField;

    @FXML
    private Label balanceLabel;

    @FXML
    private double checkUsersBalance() {
        double getBalance = Fileio.DB.getUserBalanceDB(id);
        return getBalance;
    }

    @FXML
    private void showBalance() {
        balance = checkUsersBalance();
        if (balance == -1) {
            balanceLabel.setText("User Not found!");
        } else {
            balanceLabel.setText("$" + balance);
            cridit = balance;
        }

    }

}
