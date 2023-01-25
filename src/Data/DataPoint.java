package Data;

public class DataPoint {
    private int position;
    private String athleteName;
    private int startYear;
    private int endYear;
    private int matchCount;
    private double totalMinutes;
    private double pointsPerGame;
    private double reboundsPerGame;
    private double assistsPerGame;
    private double stealsPerGame;
    private double blocksPerGame;
    private double fieldGoalPercent;
    private double threePointPercent;
    private double freeThrowPercent;
    private double careerWinShares;
    private double winSharesPer;
    private String bRefTag;
    private int addingTag;

    /**
     * starts of datapoint tag as 0
     */
    public DataPoint() {
        this.addingTag = 0;
    }


    /**
     * adds value to properties based on previously added values
     * @param value
     */
    public void add(String value) {
        switch(addingTag) {
            case 0:
                this.position = Integer.parseInt(value);
                break;
            case 1:
                this.athleteName = value;
                break;
            case 2:
                this.startYear = Integer.parseInt(value);
                break;
            case 3:
                this.endYear = Integer.parseInt(value);
                break;
            case 4:
                this.matchCount = Integer.parseInt(value);
                break;
            case 5:
                this.totalMinutes = Double.parseDouble(value); 
                break;
