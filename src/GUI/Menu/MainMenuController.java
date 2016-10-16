package GUI.Menu;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainMenuController {

    @FXML
    private static Label showTimer;

    private Main main;

    @FXML
    private void changeToCustomer() throws IOException {
        Main.showCustomerMenuScene();
    }

    @FXML
    private void changeToTopUp() throws IOException {
        Main.showTopUpMenuScene();
    }

    @FXML
    private void changeToAdmin() throws IOException {
        Main.showAdminMenuScene();
    }

    @FXML
    private void goHome() throws IOException {
        Main.showMainCenter();
    }

    @FXML
    public static void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                                showTimer.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
