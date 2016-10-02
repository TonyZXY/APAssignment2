package GUI.Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws IOException{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Main Menu");
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 800, 500));
//        primaryStage.show();
        showMainMenu();
        showMainCenter();
    }

    private void showMainMenu() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainMenu.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showMainCenter() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainCenter.fxml"));
        BorderPane mainCenter = loader.load();
        mainLayout.setCenter(mainCenter);
    }

    public static void showCustomerMenuScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/GUI/CustomerMenu/CustomerMenu.fxml"));
        BorderPane CustomerMenu = loader.load();
        mainLayout.setCenter(CustomerMenu);
    }

    public static void showTopUpMenuScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/GUI/TopUpMenu/TopUpMenu.fxml"));
        BorderPane topUpMenu = loader.load();
        mainLayout.setCenter(topUpMenu);
    }

    public static void showAdminMenuScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/GUI/AdminMenu/AdminMenu.fxml"));
        BorderPane adminMenu = loader.load();
        mainLayout.setCenter(adminMenu);
    }

    public static void showReportMenuScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/GUI/ReportMenu/ReportMenu.fxml"));
        BorderPane reportMenu = loader.load();
        mainLayout.setCenter(reportMenu);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
