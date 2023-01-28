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
            case 6:
                this.pointsPerGame = Double.parseDouble(value); 
                break;
            case 7:
                this.reboundsPerGame = Double.parseDouble(value); 
                break;
            case 8:
                this.assistsPerGame = Double.parseDouble(value); 
                break;
            case 9:
                this.stealsPerGame = Double.parseDouble(value); 
                break;
            case 10:
                this.blocksPerGame = Double.parseDouble(value); 
                break;
            case 11:
                this.fieldGoalPercent = Double.parseDouble(value); 
                break;
            case 12:
                this.threePointPercent = Double.parseDouble(value); 
                break;
            case 13:
                this.freeThrowPercent = Double.parseDouble(value); 
                break;
            case 14:
                this.careerWinShares = Double.parseDouble(value);
                break;
            case 15:
                this.winSharesPer = Double.parseDouble(value);
                break;
            case 16:
                this.bRefTag = value;
                break;
        }

        this.addingTag++;
    }

    /**
     * gets position of datapoint
     * @return int position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * gets pointsPerGame of datapoint
     * @return int pointsPerGame
     */
    public double getPointsPerGame() {
        return this.pointsPerGame;
    }

    /**
     * gets reboundsPerGame of datapoint
     * @return int reboundssPerGame
     */
    
    public double getReboundsPerGame() {
        return this.reboundsPerGame;
    }

    /**
     * gets assistsPerGame of datapoint
     * @return int assistsPerGame
     */
    public double getAssistsPerGame() {
        return this.assistsPerGame;
    }

    /**
     * gets careerWinShares of datapoint
     * @return int careerWinShares
     */
    public double getCareerWinShares() {
        return this.careerWinShares;
    }
    
    /**
     * gets athleteName 
     * @return String athleteName
     */

    public String getAthleteName() {
        return this.athleteName;
    }
}


