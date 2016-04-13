/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

/**
 *
 * @author Raja
 */
public abstract class BaseImporter implements Runnable {
    
    private String fileName;
    private ImportResult results;
    
    @Override public abstract void run();
    
    public BaseImporter(String fileName){
        this.fileName = fileName;
    }
    
    public void setResults(ImportResult results){
        this.results = results;
    }
    
    public ImportResult getResults(){
        return this.results;
    }
    
    public String getFileName(){
        return this.fileName;
    }
}
