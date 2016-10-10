package GUI.Menu;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Project APAssignment2
 * Created by TonyZheng on 4/10/16.
 */
public class DigitalClock extends Label {

    public DigitalClock() {
        bindToTime();
    }

    private void bindToTime() {
//        long endTime =Calendar.getInstance().getTimeInMillis();
//        Label timeLabel = new Label();
//        DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
//        final Timeline timeline = new Timeline( new KeyFrame( Duration.ofMillis( 500 ), event -> {
//            final long diff = endTime - System.currentTimeMillis();
//            if ( diff < 0 ) {
////        timeLabel.setText( "00:00:00" );
//                timeLabel.setText( timeFormat.format( 0 ) );
//            } else {
//                timeLabel.setText( timeFormat.format( diff ) );
//            }
//        }));
//        timeline.setCycleCount( Animation.INDEFINITE );
//        timeline.play();
//
//    private static final DateFormat DF = new SimpleDateFormat("HH:MM:SS");
//
//

    }
}
