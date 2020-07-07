//=============================================================================
// Solution Code
//=============================================================================
//=============================================================================
// PROGRAMMER:   Shmuel Weinfeld
// PANTHER ID:   3084058
//
// CLASS:        COP3337  
// SECTION:      01
// SEMESTER:     Fall 2019 
// CLASSTIME:    TuTh 3:30-4:45
//
// Project:  #1    
// DUE:       9/20/2019  
//
// CERTIFICATION: I certify that this work is my own and that
//                 none of it is the work of any other person.
//=============================================================================

package project1;

//--------------------------------------------------
// Imports
//--------------------------------------------------
import java.io.*;
import java.util.*;


public class Project1 {

   
    public static void main(String[] args) {
        
        // The name of the file to open.
        // notice that the StockPrice_X_Data.txt is in the data package
        File fileName = new File("src/data/Stock_Data.txt");

        // This will reference one line at a time
        String line ;
        ArrayList<Double> x_prices = new ArrayList<>();
        ArrayList<Double> GE_prices = new ArrayList<>();
        ArrayList<Double> APPL_prices = new ArrayList<>();
        ArrayList<Double> GOOG_prices = new ArrayList<>();
        ArrayList<Double> F_prices = new ArrayList<>();
           
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =  new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            
           //PUT CODE HERE
           
           //read the header line
           int lineCounter = 1;
           while((line = bufferedReader.readLine()) != null)
           {
               if (lineCounter != 1)
               {
                   String[] tokens = line.split(",");
                   x_prices.add(Double.parseDouble(tokens[1]));
                   GE_prices.add(Double.parseDouble(tokens[2]));
                   APPL_prices.add(Double.parseDouble(tokens[3]));
                   GOOG_prices.add(Double.parseDouble(tokens[4]));
                   F_prices.add(Double.parseDouble(tokens[5]));
                   
               }
               lineCounter ++;
           }
            // Always close files.
            bufferedReader.close(); 

            //------------------------------------------------------------------
            // Doing some calculations
            //------------------------------------------------------------------
            
			//PUT CODE HERE
           
        // handle errors if they arise
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        } catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }//end try
        
       ArrayList<Double> xCorrelations = new ArrayList<Double>();
       xCorrelations.add(findCorrelation(x_prices,x_prices));
       xCorrelations.add(findCorrelation(x_prices,GE_prices));
       xCorrelations.add(findCorrelation(x_prices,APPL_prices));
       xCorrelations.add(findCorrelation(x_prices,GOOG_prices));
       xCorrelations.add(findCorrelation(x_prices,F_prices));
       
       ArrayList<Double> GE_Correlations = new ArrayList<Double>();
       GE_Correlations.add(findCorrelation(GE_prices,x_prices));
       GE_Correlations.add(findCorrelation(GE_prices,GE_prices));
       GE_Correlations.add(findCorrelation(GE_prices,APPL_prices));
       GE_Correlations.add(findCorrelation(GE_prices,GOOG_prices));
       GE_Correlations.add(findCorrelation(GE_prices,F_prices));
       
       ArrayList<Double> APPL_Correlations = new ArrayList<Double>();
       APPL_Correlations.add(findCorrelation(APPL_prices,x_prices));
       APPL_Correlations.add(findCorrelation(APPL_prices,GE_prices));
       APPL_Correlations.add(findCorrelation(APPL_prices,APPL_prices));
       APPL_Correlations.add(findCorrelation(APPL_prices,GOOG_prices));
       APPL_Correlations.add(findCorrelation(APPL_prices,F_prices));
       
       ArrayList<Double> GOOG_Correlations = new ArrayList<Double>();
       GOOG_Correlations.add(findCorrelation(GOOG_prices,x_prices));
       GOOG_Correlations.add(findCorrelation(GOOG_prices,GE_prices));
       GOOG_Correlations.add(findCorrelation(GOOG_prices,APPL_prices));
       GOOG_Correlations.add(findCorrelation(GOOG_prices,GOOG_prices));
       GOOG_Correlations.add(findCorrelation(GOOG_prices,F_prices));
       
       ArrayList<Double> F_Correlations = new ArrayList<Double>();
       F_Correlations.add(findCorrelation(F_prices,x_prices));
       F_Correlations.add(findCorrelation(F_prices,GE_prices));
       F_Correlations.add(findCorrelation(F_prices,APPL_prices));
       F_Correlations.add(findCorrelation(F_prices,GOOG_prices));
       F_Correlations.add(findCorrelation(F_prices,F_prices));
       
       
       ArrayList<ArrayList<Double>> corrMatrix = new ArrayList<ArrayList<Double>>();
       corrMatrix.add(xCorrelations);
       corrMatrix.add(GE_Correlations);
       corrMatrix.add(APPL_Correlations);
       corrMatrix.add(GOOG_Correlations);
       corrMatrix.add(F_Correlations);
       
       for(int i = 0; i<corrMatrix.size(); i++)
       {    
            System.out.print(corrMatrix.get(i));
            
            System.out.print("\n");
       }
       
       
        

      
    }// end main()
    
//------------------------------------------------------------------------------
// helper functions/
//------------------------------------------------------------------------------
    
    public static double findAverage(ArrayList<Double> prices){
        
        double average = 0.0;
       
	   //PUT CODE HERE
           
           for(int i=0;i<prices.size();i++)
           {
                average += prices.get(i);
           }
                average /= prices.size();
       return average;
    }//end findAverage()
    
    //--------------------------------------------------------------------------
    public static double findStandardDeviation(ArrayList<Double> prices){
        
        double stdDev = 0.0;
        double average = findAverage(prices);
 	   //PUT CODE HERE
        double sum =0;   
        for(int i =0;i<prices.size();i++)
        {
            sum += Math.pow((prices.get(i)- average),2);
        }    
           sum/= prices.size();
           stdDev = Math.sqrt(sum);
	   
        return stdDev;
    }

    //--------------------------------------------------------------------------
    
    public static double findCorrelation(ArrayList<Double> firstPrices, ArrayList<Double> secondPrices ){
        
        double correlation;
        double sumPrices = 0.0;
        
        double firstAvg = findAverage(firstPrices);
        double firstStdDev = findStandardDeviation(firstPrices);
        double secondAvg = findAverage(secondPrices);
        double secondStdDev = findStandardDeviation(secondPrices);//
        for(int i = 0; i< firstPrices.size(); i++)
        {
            sumPrices += (firstPrices.get(i)- firstAvg)*(secondPrices.get(i)- secondAvg);
        } 
            
            correlation = (sumPrices/(firstStdDev*secondStdDev))/(firstPrices.size()-1);
            
            return correlation;
    }//end findCorrelation()
    
    
}
