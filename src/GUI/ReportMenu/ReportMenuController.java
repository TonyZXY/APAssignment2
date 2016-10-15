package GUI.ReportMenu;

import Fileio.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Project APAssignment2
 * Created by TonyZheng on 14/10/2016.
 */
public class ReportMenuController {
    @FXML
    private TextArea reportArea;

    @FXML
    private Button travelPassBtn;

    @FXML
    private Button stationBtn;

    @FXML
    private void setTravelPassBtn(){
        Fileio.DB.allTravelReport();
    }

    @FXML
    private void setStationBtn(){

    }
}
