/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

import java.util.ArrayList;

/**
 *
 * @author Raja
 */
public class ImportResult {
    
    private int totalRows;
    private int importedRows;
    private int failedRows;
    private ArrayList<String> errorMessages = new ArrayList<String>();
    
    public ImportResult(){
        this.setTotalRows(0);
        this.setImportedRows(0);
        this.setFailedRows(0);
        this.errorMessages.clear();
    }
    
    public void setTotalRows(int totalRows){
        this.totalRows = totalRows;
    }
    
    public int getTotalRows(){
        return this.totalRows;
    }
    
    public void setImportedRows(int importedRows){
        this.importedRows = importedRows;
    }
    
    public int getImportedRows(){
        return this.importedRows;
    }
    
    public void setFailedRows(int failedRows){
        this.failedRows = failedRows;
    }
    
    public int getFailedRows(){
        return this.failedRows;
    }
    
    public void setErrorMessages(String ErrorMessage){
        this.errorMessages.add(ErrorMessage);
    }
    
    public ArrayList<String> getErrorMessages(){
        return this.errorMessages;
    }
}
