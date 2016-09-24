package GUI.MainMenu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class MainMenuButton extends Application{
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getPage(), 900, 500);
        primaryStage.setTitle("MyTi.MyTiSystem - Main menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected Text text = new Text(300, 20, "Welcome to Using MyTi.MyTiSystem.");

    protected BorderPane getPage() {
        VBox paneForButtons = new VBox(50);
        Button purchase = new Button("Purchase");
        Button admin = new Button("Admin");
        Button others = new Button("Others");
        paneForButtons.getChildren().addAll(purchase, admin,others);
//        paneForButtons.setHgrow(paneForButtons, Priority.ALWAYS);
//        paneForButtons.layout();
//        paneForButtons.setVgrow(paneForButtons,Priority.ALWAYS);
        paneForButtons.setAlignment(Pos.BASELINE_CENTER);
        paneForButtons.setStyle("-fx-background-color: aqua");
        paneForButtons.setStyle("-fx-border-color: blue");
        BorderPane pane = new BorderPane();
        pane.setLeft(paneForButtons);
        Pane paneForText = new Pane();
        paneForText.getChildren().add(text);
        pane.setCenter(paneForText);
//        btLeft.setOnAction(e -> text.setX(text.getX() - 10));
//        btRight.setOnAction(e -> text.setY(text.getY() + 10));
        return pane;
    }

    int zone;
    char duration;


    public static void main(String[] args) {
        launch(args);
    }
}
