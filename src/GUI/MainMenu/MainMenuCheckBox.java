package GUI.MainMenu;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class MainMenuCheckBox extends MainMenuButton {
//    @Override // Override the getPane() method in the super class
    protected BorderPane getPane() {
        BorderPane pane = super.getPage();
        VBox paneForCheckBoxes = new VBox(20);
        paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));
        CheckBox chkZone1 = new CheckBox("Zone 1");
        CheckBox chkZone2 = new CheckBox("Zone 2");

        paneForCheckBoxes.getChildren().addAll(chkZone1, chkZone2);
        pane.setCenter(paneForCheckBoxes);
        EventHandler<ActionEvent> handler = e -> {
            if (chkZone1.isSelected()) {
                zone = 1;
            } else if (chkZone2.isSelected()) {
                zone = 2;
            }
        };
        chkZone1.setOnAction(handler);
        chkZone2.setOnAction(handler);

        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
