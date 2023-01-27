package CreateChart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import java.io.*;
import java.util.*;
import javafx.scene.layout.HBox;
import data.Sorter;
import data.GraphType;
import data.DataPoint;
import javafx.geometry.Insets;


import data.CSVScraper;  
 
/**
 * A line chart demonstrating a CategoryAxis implementation.
 */
public class ChartGenerator {
 
    private boolean showPoints;
    private boolean showAssists;
    private boolean showRebounds;
    private boolean showWinShares;
    private int showRank;
    private CSVScraper scraper;
    private int startRank;
    private int endRank;
    private Sorter sorter;
    private HBox lineChart;
    private HBox barChart;
    private int barStart;
    private GraphType barType;

    /**
     * Constructor automatically creates a CSVScraper object when created
     */
    public ChartGenerator () {
        this.scraper = new CSVScraper();
        this.showPoints = false;
        this.showAssists = false;
        this.showWinShares = false;
        this.showRebounds = false;
        this.startRank = 0;
        this.endRank = 0;
        this.showRank = 0;
        this.barStart = 1;
        this.barType = GraphType.BYRANK;
        this.sorter = new Sorter();
        this.lineChart = new HBox();
        this.lineChart.setPadding(new Insets(50));
        this.barChart = new HBox();
        this.barChart.setPadding(new Insets(50));
    }

    
    /**
     * Creates Bar Chart based on inputs from user
     * @return Parent chart 
     */

    public Parent createBarChart() {
        BarChart chart;
        CategoryAxis xAxis;
        NumberAxis yAxis;

        //adds objects into sorter object
        for(int i = 0; i < 500; i++) {
            sorter.addSort(barType, scraper.get(i));
        }

        DataPoint[] player = new DataPoint[5];

        //gets sorted array from sorter object
        ArrayList<DataPoint> playerList = (ArrayList<DataPoint>) sorter.getArray().clone();

        

        //puts 5 diplayed players into array
        for(int i = 0; i < 5; i++) {
            player[i] = playerList.get(500 - barStart - (i));
            
        }


        //puts players names into axis
        String[] playerNames = {player[0].getPlayerName(), player[1].getPlayerName(), player[2].getPlayerName(), player[3].getPlayerName(), player[4].getPlayerName()};
        xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(playerNames));
        yAxis = new NumberAxis("Stat Value", 0.0d, 50.0d, 5.0d);


        //creates bar chart for given players
        ObservableList<BarChart.Series> barChartData =
            FXCollections.observableArrayList(
                new BarChart.Series("Points",
                                    FXCollections.observableArrayList(
                    new BarChart.Data(player[0].getPlayerName(), player[0].getPointsPerGame()),
                    new BarChart.Data(player[1].getPlayerName(), player[1].getPointsPerGame()),
                    new BarChart.Data(player[2].getPlayerName(), player[1].getPointsPerGame()),
                    new BarChart.Data(player[3].getPlayerName(), player[1].getPointsPerGame()),
                    new BarChart.Data(player[4].getPlayerName(), player[4].getPointsPerGame()))),
                new BarChart.Series("Assists",
                                    FXCollections.observableArrayList(
                    new BarChart.Data(player[0].getPlayerName(), player[0].getAssistPerGame()),
                    new BarChart.Data(player[1].getPlayerName(), player[1].getAssistPerGame()),
                    new BarChart.Data(player[2].getPlayerName(), player[1].getAssistPerGame()),
                    new BarChart.Data(player[3].getPlayerName(), player[1].getAssistPerGame()),
                    new BarChart.Data(player[4].getPlayerName(), player[4].getAssistPerGame()))),
                new BarChart.Series("Rebounds",
                                    FXCollections.observableArrayList(
                    new BarChart.Data(player[0].getPlayerName(), player[0].getReboundsPerGame()),
                    new BarChart.Data(player[1].getPlayerName(), player[1].getReboundsPerGame()),
                    new BarChart.Data(player[2].getPlayerName(), player[1].getReboundsPerGame()),
                    new BarChart.Data(player[3].getPlayerName(), player[1].getReboundsPerGame()),
                    new BarChart.Data(player[4].getPlayerName(), player[4].getReboundsPerGame())))
            );
