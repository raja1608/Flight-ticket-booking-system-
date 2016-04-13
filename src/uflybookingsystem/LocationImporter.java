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
import uflybookingsystem.BusinessObjects.Location;

/**
 *
 * @author Raja
 */
public class LocationImporter extends BaseImporter {

    public LocationImporter(String fileName){
        super(fileName);
    }
   
    
    @Override public void run(){
        //Assigning the results to instance of ImportResult.
        ImportResult result =  super.getResults();
        
        //Reading the file with the filereader and firstline check
        try(FileReader fileReader = new FileReader(super.getFileName());){
            int a;
            String fileData = "";
            while ((a = fileReader.read()) != -1){
                fileData += (char)a;
            }
            String[] lines = fileData.replace("\r\n", "\n").replace("\r","\n").split("\n");
            
            //step 28
            String firstLine = lines[0];
            String[] columns =firstLine.split(",");
            ArrayList<String> linesList = new ArrayList<String>(Arrays.asList(lines));
            if(columns.length == 2){
                if(columns[0].equals("City")  && columns[1].equals("Airport Code") ){
                   linesList.remove(0);
                }
            }
            
            int lineNum, totalRows,failedRows,importedRows;
            lineNum = 1;
            totalRows = failedRows = importedRows = 0;
            for(String line : linesList){
                try{
                  if (line.trim().isEmpty()){
                      //line is blank no need to import
                      continue;
                  }
//                  else if(line.isEmpty()){
//                      continue;
//                  }
                  else{
                     //int totalRows = result.getTotalRows();
                     totalRows++;
                     //result.setTotalRows(totalRows);
                  }
                  //Step 29
                  columns = line.split(",");
                  if(columns.length != 2){
                      failedRows++;
                      result.setErrorMessages("The number of columns in the line no " + lineNum + " is in correct");
                      continue;
                  }
                  //Step 30
                  if(columns[0].isEmpty()){
                      failedRows++;
                      result.setErrorMessages("The value of the City column in the line no " + lineNum + " is empty.");
                      continue;
                  }
                  //Step 31
                  if(columns[1].isEmpty()){
                      failedRows++;
                      result.setErrorMessages("The value of the Airport Code column in the line no " + lineNum + " is empty.");
                      continue;
                  }
                  //step32
                  if(columns[1].matches("\\w{3,}")){
                      failedRows++;
                      result.setErrorMessages("The Airport Code in the line no " + lineNum + " is not in the valid format.");
                      continue;
                  }
                  //Step 33,34,35
                Location locationToUpdate = DatabaseOperations.getLocationByAirportCode(columns[1]);
                if(locationToUpdate == null){
                    Location locationToAdd = new Location();
                    locationToAdd.setCity(columns[0]);
                    locationToAdd.setAirportCode(columns[1]);
                    DatabaseOperations.AddLocation(locationToAdd);
                    //check the next two lines with the book
                    ArrayList<Location> locationsList = new ArrayList<Location>();
                    locationsList.add(locationToAdd);
                }
                else{
                    locationToUpdate.setCity(columns[0]);
                    DatabaseOperations.UpdateLocation(locationToUpdate);
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
             
        } catch(IOException ioex) {
            ioex.printStackTrace();
            result.setErrorMessages("An error/exception occured during the input/output operation");
        }
        catch(Exception e){
            e.printStackTrace();
            result.setErrorMessages("An exception occured during the import");
        }
    } 
}
