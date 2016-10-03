package GUI.Menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Calendar;

public class MainMenuController {

    @FXML
    private Label showTimer;

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

    @FXML
    private void showTime() throws Exception{
        Calendar time = Calendar.getInstance();
        String hourString = pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
        String minuteString = pad(2, '0', time.get(Calendar.MINUTE) + "");
        String secondString = pad(2, '0', time.get(Calendar.SECOND) + "");
        String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        showTimer.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
    }

    private static String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }
}
