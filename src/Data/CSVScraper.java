import java.io.*;
import java.util.*;


public class CSVScraper  {
    private List<DataPoint> data;

    public CSVScraper() {
         //create buffered reader for stats file
         try {
            BufferedReader br = new BufferedReader(new FileReader("src/Data/stats.csv"));
            String line =  null;
   
             //ArrayList used for containing data
             List<DataPoint> data = new ArrayList<DataPoint>();
             int j = 0;
   
             //while there are more rows to go through
             while((line = br.readLine()) != null){
                //split the given line by the commas that separate them
                String[] str = line.split(",");
    
                //convert array into an arraylist
                DataPoint datapoint = new DataPoint();
                if(j > 0) {
                  for(int i = 0; i < str.length; i++) {
                     if(!str[i].equals("")) {
                        datapoint.add(str[i]);
      
                     } else {
                        datapoint.add("-1");
                     }
                     
                     
                  }

                  //add datapoint into arraylist
                  data.add(datapoint);

                }

                j++;
                     
             }
    
             this.data = data;

         } catch (Exception e) {
            System.out.println("d");
         }     
        
   }

   public DataPoint get(int i) {
      return data.get(i);
   }

   



   
   
 

   
}