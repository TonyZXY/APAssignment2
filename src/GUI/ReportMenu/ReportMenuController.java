package GUI.ReportMenu;

import Fileio.DB;
import MyTi.MyTiSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

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
        reportArea.clear();
        ArrayList<String> report = Fileio.DB.allTravelReport();
        reportArea.appendText("Travel Pass Report");
        for (String aReport : report) {
            reportArea.appendText(aReport);
        }
    }

    @FXML
    private void setStationBtn(){
        reportArea.clear();
        ArrayList<String> report = MyTiSystem.generateStationsStatistics();
        reportArea.appendText("Station report");
        for(String aReport:report){
            reportArea.appendText(aReport);
        }
    }
}
