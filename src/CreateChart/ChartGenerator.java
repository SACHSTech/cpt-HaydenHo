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
        chart = new BarChart(xAxis, yAxis, barChartData, 25.0d);

        chart.setPrefWidth(2500);

        if(barType == GraphType.BYRANK) {
            chart.setTitle("Stats of " + (barStart) + " to " + (barStart + 4) + " Ranked Players");
        } else if(barType == GraphType.BYPOINTS) {
            chart.setTitle("Stats of " + (barStart) + " to " + (barStart + 4) + " Scorers");
        } else if(barType == GraphType.BYASSISTS) {
            chart.setTitle("Stats of " + (barStart) + " to " + (barStart + 4) + " Assisters");
        } else if(barType == GraphType.BYREBOUNDS) {
            chart.setTitle("Stats of " + (barStart) + " to " + (barStart + 4) + " Rebounders");
        }
         
        //adds chart to hbox
        this.barChart.getChildren().clear();
        this.barChart.getChildren().add(chart);


        //returns hbox
        return this.barChart;
    }


    /**
     * Creates a Linechart based of ranks the user wants to see and the stats chosen
     * @param startRank starting rank plotted on graph
     * @param endRank ending rank plotted on graph
     */
    public HBox createRankLineChart() {
        //creates hbox object for linechart
        HBox currentChart = new HBox();
        currentChart.setPrefWidth(1700);
        currentChart.setMinWidth(1000);
        currentChart.setMaxWidth(2500);
        currentChart.setPadding(new Insets(50));

        //creates default axis
        NumberAxis xAxis = new NumberAxis("Rank", startRank, endRank, 1);
        NumberAxis yAxis = new NumberAxis("Value", 0, 100, 0.1);
        


        ObservableList<XYChart.Series<Double,Double>> lineChartData =
            FXCollections.observableArrayList();

        //add lines depending on selected stats
        if(showRank != 0) {
            lineChartData.add(this.rankSeries());
            
            //creates appropriate axis
            NumberAxis xAxis1 = sorter.axis("Stat Value");
            xAxis = xAxis1;
            yAxis = new NumberAxis("Rank", 0, 500, 1);
        } else {
            //adds stat series to linechart and updates axis based on user input
            if(showPoints) {
                lineChartData.add(this.pointsSeries(startRank, endRank));

                NumberAxis yAxis1 = sorter.axis("Value");
                yAxis = yAxis1;

            } if(showAssists) {
                lineChartData.add(this.assistsSeries(startRank, endRank));

                NumberAxis yAxis1 = sorter.axis("Value");
                yAxis = yAxis1;

            } if(showRebounds) {
                lineChartData.add(this.reboundsSeries(startRank, endRank));

                NumberAxis yAxis1 = sorter.axis("Value");
                yAxis = yAxis1;

            } if(showWinShares) {
                lineChartData.add(this.winshareSeries(startRank, endRank));

                NumberAxis yAxis1 = sorter.axis("Value");
                yAxis = yAxis1;

            }
        }

        
       

        //create and return new LineChart object
        LineChart chart = new LineChart(xAxis, yAxis, lineChartData);


        //Creates a title based on the selected stats
        String title = "";
        if(showRank != 0) {
            title += "Rank vs ";
            switch(showRank) {
                case 1:
                    title += "Points";
                    break;
                case 2:
                    title += "Assists";
                    break;
                case 3:
                    title += "Rebounds";
                    break;
                case 4:
                    title += "Career Win Shares";
                    break;
                
            }
        } else if(showPoints || showAssists || showRebounds || showWinShares) {
            if(showPoints) {
                title += "Points, ";
                
            } if(showAssists) {
                title += "Assists, ";
                
            } if(showRebounds) {
                title += "Rebounds, ";
                
            } if(showWinShares) {
                title += "Win Shares, ";
               
            }

            title += "by Rank";

            
        }
        //resets sorter object
        this.sorter = new Sorter();

        title += " on SLAM's 2011 Top 500 Players";

        //edits chart properties then adds it to hbox
        chart.setTitle(title);
        chart.setPrefWidth(2500);
        this.lineChart.getChildren().clear();
        this.lineChart.getChildren().add(chart);

        return lineChart;
    }
    
    /**
     * Update whether chart contains following series
     * @param boolean showPoints 
     * @param boolean showAssists
     * @param boolean showRebounds
     * @param boolean showWinShares
     */
    public void updateBooleans(boolean showPoints, boolean showAssists, boolean showRebounds, boolean showWinShares) {
        this.showPoints = showPoints;
        this.showAssists = showAssists;
        this.showRebounds = showRebounds;
        this.showWinShares = showWinShares;

        this.createRankLineChart();

    }


    /**
     * Changes bar chart when user requests
     * @param graphType
     * @param starting
     */

    public void changeBarChart(GraphType graphType, int starting) {
        this.barType = graphType;
        this.barStart = starting;
        this.createBarChart();
    }


    /**
     * Changes (points/rebounds/assists/winshares) vs rank
     * @param rank
     */

    public void setShowRank(int rank) {
        this.showRank = rank;
        //creates type of linechart 
        if(rank != 0) {
            this.createRankLineChart();
        }
    }
    
    /**
     * Creates a LineChart Series for Points
     * @param startRank starting rank in series
     * @param endRank ending rank in series
     * @return LineChart.Series<Double, Double> object
     */
    public LineChart.Series<Double, Double> pointsSeries(int startRank, int endRank) {
        ObservableList<XYChart.Data<Double, Double>> pointsData = FXCollections.observableArrayList();

        //loops through DataPoint object and adds data to series
        for(int i = startRank - 1; i < endRank; i++) {
            pointsData.add(new XYChart.Data<>((double) i + 1, sorter.addSort(GraphType.BYPOINTS, scraper.get(i))));

            
        }

        LineChart.Series<Double, Double> points = new LineChart.Series<>("Points Per Game", pointsData);

        return points;
    }

    /**
     * Creates series for (points/assists/rebounds/winshares) vs rank
     * @return LineChart.Series<Double, Double> rankData
     */
    public LineChart.Series<Double, Double> rankSeries() {
        ObservableList<XYChart.Data<Double, Double>> rankData = FXCollections.observableArrayList();

        //Creates dataSeries based on type of graph user wants to see
        for(int i = 0; i < 500; i++) {
            switch(this.showRank) {
                case 1:
                    rankData.add(new XYChart.Data<>(sorter.addSort(GraphType.BYPOINTS, scraper.get(i)), (double) scraper.get(i).getRank()));
                    break;
                case 2:
                    rankData.add(new XYChart.Data<>(sorter.addSort(GraphType.BYASSISTS, scraper.get(i)), (double) scraper.get(i).getRank()));
                    break;
                case 3:
                    rankData.add(new XYChart.Data<>(sorter.addSort(GraphType.BYREBOUNDS, scraper.get(i)), (double) scraper.get(i).getRank()));
                    break;
                case 4:
                    rankData.add(new XYChart.Data<>(sorter.addSort(GraphType.BYWINSHARES, scraper.get(i)), (double) scraper.get(i).getRank()));
                    break;
            
            }
            
        }

        LineChart.Series<Double, Double> rank = new LineChart.Series<>("Rank", rankData);

        return rank;
    }

    
    /**
     * Creates a LineChart Series for assists
    * @param startRank starting rank in series
     * @param endRank ending rank in series
     * @return LineChart.Series<Double, Double> object
     */
    public LineChart.Series<Double, Double> assistsSeries(int startRank, int endRank) {
        ObservableList<XYChart.Data<Double, Double>> assistsData = FXCollections.observableArrayList();

        //loops through DataPoint object and adds data to series
        for(int i = startRank - 1; i < endRank; i++) {
            assistsData.add(new XYChart.Data<>((double) i + 1, sorter.addSort(GraphType.BYASSISTS, scraper.get(i))));
        }

        LineChart.Series<Double, Double> assists = new LineChart.Series<>("Assists Per Game", assistsData);

        return assists;
    }

