package CreateChart;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 
import javafx.scene.control.Slider;
import javafx.beans.value.*;
import javafx.scene.control.Button;
import java.util.*;
import data.GraphType;

import javafx.event.ActionEvent;

 
/**
 * An example of radio buttons in various states.
 */

public class ChartValueControl {

    private VBox lineControls;
    private VBox barControls;
    private boolean secondaryPanel;
    private ArrayList<Boolean> rankBooleans;
    private ChartGenerator chartObject;

    

    public ChartValueControl () {
        lineControls = new VBox(18);
        lineControls.setPadding(new Insets(100, 50, 50, 50));
        barControls = new VBox(18);
        barControls.setAlignment(Pos.CENTER);
        barControls.setPadding(new Insets(100, 50, 50, 50));
        rankBooleans = new ArrayList<>();
        rankBooleans.add(false);
        rankBooleans.add(false);
        rankBooleans.add(false);
        rankBooleans.add(false);
    }

    /**
     * Creates prompt for input
     */
 
    public void initialLineControl() {
        //Creates VBox to put linechart object to put in
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER_LEFT);
        

        //Create elements to put into control box

        //Header
        Text txt = new Text();
        txt.setText("X-Axis");

        //Toggle Group with buttons with it 
        ToggleGroup tg = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Rank");
        rb1.setToggleGroup(tg);
 
        RadioButton rb2 = new RadioButton("Points");
        rb2.setToggleGroup(tg);
 
        RadioButton rb3 = new RadioButton("Assists");
        rb3.setToggleGroup(tg);

        RadioButton rb4 = new RadioButton("Rebounds");
        rb4.setToggleGroup(tg);

        RadioButton rb5 = new RadioButton("Win Shares");
        rb5.setToggleGroup(tg);

        

        Text text = new Text();      
      
        //Setting the text to be added. 
        text.setText("Starting Rank"); 

        //Sliders for Ranks to show on graph

        Slider fromSlider = new Slider(1, 500, 50);
        fromSlider.setShowTickMarks(true);
        fromSlider.setPrefWidth(200);
        fromSlider.setShowTickLabels(true);
        fromSlider.setMajorTickUnit(50f);
        fromSlider.setBlockIncrement(50f);
        fromSlider.setDisable(true);

        Text text1 = new Text();      
      
        //Setting the text to be added. 
        text1.setText("Ending Rank");

        Slider toSlider = new Slider(1, 500, 50);
        toSlider.setShowTickMarks(true);
        toSlider.setPrefWidth(200);
        toSlider.setShowTickLabels(true);
        toSlider.setMajorTickUnit(50f);
        toSlider.setBlockIncrement(50f);
        toSlider.setDisable(true);


        

}
