package GUI.Menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {

    private Main main;
    @FXML
    private void changeToCustomer() throws IOException{
        main.showCustomerMenuScene();
    }

    @FXML
    private void changeToTopUp() throws IOException{
        main.showTopUpMenuScene();
    }

    @FXML
    private void changeToAdmin() throws IOException{
        main.showAdminMenuScene();
    }

    @FXML
    private void goHome() throws IOException {
        main.showMainCenter();
    }
}
