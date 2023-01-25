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


/**
 * adds datapoint to arraylist and returns its values
 * @param graphtype
 * @param datapoint
 * @return double stat values of stat is returned 
 */
public double addSort(GraphType graphtype, DataPoint datapoint) {
    //adds value to object's arraylist, then returns value inputted back 
    this.sortingArray.add(datapoint);
    if(graphtype == GraphType.BYPOINTS) {
        this.sortingBy = GraphType.BYPOINTS;
        return datapoint.getPpg();
    } else if (graphtype == GraphType.BYASSISTS) {
        this.sortingBy = GraphType.BYASSISTS;
        return datapoint.getApg();
    } else if (graphtype == GraphType.BYREBOUNDS) {
        this.sortingBy = GraphType.BYREBOUNDS;
        return datapoint.getRpg();
    } else if (graphtype == GraphType.BYWINSHARES) {
        this.sortingBy = GraphType.BYWINSHARES;
        return datapoint.getCareerWinShares();
    } else {
        return 0.0;
    }
    
}

/**
 * allows sorting algorithm to get appropriate value from datapoint
 * @param DataPoint datapoint 
 * @return double 
 */

public double getValue(DataPoint dataPoint) {
    //returns appropriate value depending on what the user asks for 
    if(sortingBy == GraphType.BYPOINTS) {
        return dataPoint.getPpg();
    } else if(sortingBy == GraphType.BYASSISTS) {
        return dataPoint.getApg();
    } else if(sortingBy == GraphType.BYREBOUNDS) {
        return dataPoint.getRpg();
    } else if(sortingBy == GraphType.BYWINSHARES) {
        return dataPoint.getCareerWinShares();
    } else {
        return 0.0;
    }


}



