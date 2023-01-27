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

   