/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import uflybookingsystem.BusinessObjects.Flight;
import uflybookingsystem.BusinessObjects.Plane;

/**
 *
 * @author Raja
 */
public class FlightImporter extends BaseImporter {

    public FlightImporter(String fileName){
        super(fileName);
    }
    
    
    @Override public void run() {
        //Assigning the results to instance of ImportResult.
        ImportResult result =  super.getResults();
        
        try(FileReader fileReader = new FileReader(super.getFileName());){
            int a;
            String fileData = "";
            while ((a = fileReader.read()) != -1){
                fileData += (char)a;
            }
            String[] lines = fileData.replace("\r\n", "\n").replace("\r","\n").split("\n");
            
            String firstLine = lines[0];
            String[] columns =firstLine.split(",");
            ArrayList<String> linesList = new ArrayList<String>(Arrays.asList(lines));
            if(columns.length == 7){
                if(columns[0].equals("Flight Number")  && columns[1].equals("Departure Airport") 
                        && columns[2].equals("Destination Airport") && columns[3].equals("Price")
                        && columns[4].equals("DateTime") && columns[5].equals("Plane") &&
                        columns[6].equals("Seats Taken")){
                   linesList.remove(0);
                }
            }

            int lineNum, totalRows,failedRows,importedRows;
            lineNum = 1;
            totalRows = failedRows = importedRows = 0;
            for(String line : linesList){
                try{
                    if (line.trim().isEmpty()){
                      continue;
                    }
                    else{
                        totalRows++;
                    }
                    //Spliting each line of text by comma
                    columns = line.split(",");
                    if(columns.length != 7){
                      failedRows++;
                      result.setErrorMessages("The number of columns in the line no " + lineNum + " is in correct");
                      continue;
                    }
                    
                    if(columns[0].isEmpty()){
                      failedRows++;
                      result.setErrorMessages("The Flight Number in the line no " + lineNum + " is empty.");
                      continue;
                    }
                    
                    if(columns[1].isEmpty()){
                      failedRows++;
                      result.setErrorMessages("The Departure Airport in the line no " + lineNum + " is empty.");
                      continue;
                    }
                    
                    if(columns[2].isEmpty()){
                      failedRows++;
                      result.setErrorMessages("The Destination Airport in the line no " + lineNum + " is empty.");
                    }
                    
                    if(columns[5].isEmpty()){
                      failedRows++;
                      result.setErrorMessages("The Plane in the line no " + lineNum + " is empty.");
                      continue;
                    }
                    
                    if(columns[0].matches("\\w{3,}")){ //check regex, its wrong now
                        failedRows++;
                        result.setErrorMessages("The Flight Number in the line no " + lineNum + " is not in the valid format.");
                        continue;
                    }
                    
                    if(columns[1].matches("\\w{3,}")){ 
                        failedRows++;
                        result.setErrorMessages("The Departure Airport in the line no " + lineNum + " is not in the valid format.");
                        continue;
                    }
                    
                    if(columns[2].matches("\\w{3,}")){ 
                        failedRows++;
                        result.setErrorMessages("The Destination Airport in the line no " + lineNum + " is not in the valid format.");
                        continue;
                    }
                    
                Flight flightToUpdate = DatabaseOperations.getFlightByFLightNumber(columns[0]);
                if(flightToUpdate == null){
                     Flight flightToAdd = new Flight();
                     flightToAdd.setFlightNumber(columns[0]);
                     flightToAdd.setDepartureAirport(columns[1]);
                     flightToAdd.setDestinationAirport(columns[2]);
                     flightToAdd.setPrice(Double.parseDouble(columns[3]));
                     flightToAdd.setDateTime(columns[4]);
                     flightToAdd.setPlane(columns[5]);
                     flightToAdd.setSeatsTaken(Integer.parseInt(columns[6]));
                     DatabaseOperations.AddFlight(flightToAdd);
                }
                else{
                     flightToUpdate.setDepartureAirport(columns[1]);
                     flightToUpdate.setDestinationAirport(columns[2]);
                     flightToUpdate.setPrice(Double.parseDouble(columns[3]));
                     flightToUpdate.setDateTime(columns[4]);
                     flightToUpdate.setPlane(columns[5]);
                     flightToUpdate.setSeatsTaken(Integer.parseInt(columns[6]));
                     DatabaseOperations.UpdateFlight(flightToUpdate);
                }
                importedRows++;  
                  
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    lineNum++;
                }  
            }
           result.setTotalRows(totalRows);
           result.setFailedRows(failedRows);
           result.setImportedRows(importedRows);
        }
        catch(IOException ioex) {
            ioex.printStackTrace();
            result.setErrorMessages("An error/exception occured during the input/output operation");
        }
        catch(Exception e){
            e.printStackTrace();
            result.setErrorMessages("An exception occured during the import");
        }
    }
    
}
