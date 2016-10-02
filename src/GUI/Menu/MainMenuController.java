package GUI.Menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {

    private Main main;
    @FXML
    private void changeToCustomer() throws IOException{
        Main.showCustomerMenuScene();
    }

    @FXML
    private void changeToTopUp() throws IOException{
        Main.showTopUpMenuScene();
    }

    @FXML
    private void changeToAdmin() throws IOException{
        Main.showAdminMenuScene();
    }

    @FXML
    private void goHome() throws IOException {
        Main.showMainCenter();
    }
}
