import java.util.ArrayList;
import java.text.DateFormat;
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    // initializes the names of log files.

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fname)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fname);
        

    }


    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    public int numberofAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++){
            total = total + hourCounts[hour];
        }
        return total;
    }
    public int busiestHour()
    {
        int busiestHour = 0;
        int numberofAccesses = 0;
        for(int hour = 0; hour < hourCounts.length; hour++){
            if(numberofAccesses < hourCounts[hour]) {
                numberofAccesses = hourCounts[hour];
                busiestHour = hour;
            }
        }
        return busiestHour;
    }
    public int BusiestTwoHour()
    {
        int busiestHour = 0;
        int busiestHour2 = 0;
        int numberofAccesses = 0;
        for(int hour = 0; hour <= hourCounts.length; hour++) {
          if( numberofAccesses < hourCounts[hour]) {
              numberofAccesses =  hourCounts[hour];
              busiestHour = hour;
            }    
        }
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if(numberofAccesses < hourCounts[hour]) {
                numberofAccesses = hourCounts[hour];
                busiestHour2 = hour;
            }
        }
        return busiestHour;
    }
    public int quietestHour()
    {
        int quietestHour = 0;
        int numberofAccesses = hourCounts[0];
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(numberofAccesses > hourCounts[hour]){
                numberofAccesses = hourCounts[hour];
                quietestHour = hour;
            }
        }
        return quietestHour;
    }
}
