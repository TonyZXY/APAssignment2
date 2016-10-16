package GUI.AdminMenu;

import GUI.Menu.Main;
import MyTi.MyTiSystem;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Project APAssignment2
 * Created by TonyZheng on 2/10/16.
 */
public class AdminMenuController {
    private Main main;
    private boolean Valid = false;

    @FXML
    private TextField AdminID;

    @FXML
    private TextField AdminPassword;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField openBalanceField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField Zone12H;

    @FXML
    private TextField Zone11D;

    @FXML
    private TextField Zone22H;

    @FXML
    private TextField Zone21D;

    @FXML
    private void continueBTN(){
        if((AdminID.getText().equals("Admin"))&&(AdminPassword.getText().equals("Admin"))){
            Valid = true;
        }
    }

    @FXML
    private void addNewUser() throws IOException{
        if(Valid){
            String id = idField.getText();
            String name = nameField.getText();
            String email = EmailField.getText();
            double balance = Double.parseDouble(openBalanceField.getText());
            char type = typeField.getText().charAt(0);
            Fileio.DB.addNewUserDB(id,name,balance,email,type);
        }else {
            changeToMainMenu();
        }
    }

    @FXML
    private void changePrice() throws IOException{
        if (Valid){
            double z12H = Double.parseDouble(Zone12H.getText());
            double z11D = Double.parseDouble(Zone11D.getText());
            double z22H = Double.parseDouble(Zone22H.getText());
            double z21D = Double.parseDouble(Zone21D.getText());
            MyTiSystem.change(z12H,z11D,z22H,z21D);
        }else{
            changeToMainMenu();
        }
    }

    @FXML
    private void changeToMainMenu() throws IOException{
        Main.showMainCenter();
    }

    @FXML
    private void changeToReportMenu() throws IOException {
        Main.showReportMenuScene();
    }
}
