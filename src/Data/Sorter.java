package Data;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.NumberAxis;

import java.util.*;

public class Sorter {
private ArrayList<DataPoint> sortingArray;
private GraphType sortingBy;
private double currentLowest;
private double currentHighest;

public Sorter() {
    sortingArray = new ArrayList<>();
    currentHighest = 15;
    currentLowest = 15;
}


