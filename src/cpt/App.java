package cpt;

import ChartsManagement.ChartGenerator;
import ChartsManagement.ChartValueController;
import data.CSVScraper;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;


/**
 * A line chart demonstrating a CategoryAxis implementation.
 */
public class App extends Application {

    /**
     * Overriding JavaFX Application Class method to run wanted script
     */

    @Override public void start(Stage primaryStage) throws Exception {
        //creates objects to create chart/controls
        ChartGenerator chart = new ChartGenerator();        
        ChartValueControl controller = new ChartValueControl();
        controller.addChartObject(chart);
        
        //creates tabs for linechart 
        Tab lineChart = new Tab("Line Chart");
        lineChart.setClosable(false);

        //adds linechart and controls to hbox within tab
        HBox layoutLineChart = new HBox(chart.createRankLineChart(), controller.lineControl());

        //adds linechart content to tab
        lineChart.setContent(layoutLineChart);

        //creates tabs for barcharts
        Tab barChart = new Tab("Bar Chart");
        barChart.setClosable(false);

        //adds barchart and controls to hbox within tab
        HBox layoutBarChart = new HBox(chart.createBarChart(), controller.barControl());

        barChart.setContent(layoutBarChart);

        


        //combines tab panes and puts it into stage 
        TabPane tabpane = new TabPane(lineChart, barChart);

        HBox layout = new HBox(tabpane);
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);

        primaryStage.setTitle("SLAM Top 500 Charting");
        primaryStage.show();

    
        
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);

       
        
    }
}
