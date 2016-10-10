package GUI.AdminMenu;

import GUI.Menu.Main;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Project APAssignment2
 * Created by TonyZheng on 2/10/16.
 */
public class AdminMenuController {
    private Main main;

    @FXML
    private void changeToReportMenu() throws IOException{
        Main.showReportMenuScene();
    }
}
