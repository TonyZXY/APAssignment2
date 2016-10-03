package GUI.CustomerMenu;

import GUI.Menu.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Project APAssignment2
 * Created by TonyZheng on 3/10/16.
 */
public class CustomerMenuController {
    @FXML
    private Label balance;

    @FXML
    private void showBalance(){
    }

    @FXML
    private void changeToTopUp() throws IOException {
        Main.showTopUpMenuScene();
    }
}
