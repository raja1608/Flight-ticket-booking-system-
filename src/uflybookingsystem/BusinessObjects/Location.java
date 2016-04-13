/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem.BusinessObjects;

/**
 *
 * @author Raja
 */
public class Location {
    
    private String city;
    private String airportCode;
    
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public void setAirportCode(String aiportCode){
        this.airportCode = aiportCode;
    }
    
    public String getAirportCode(){
        return this.airportCode;
    }
    
    @Override public String toString(){
        return this.getCity() + " " + this.getAirportCode();
    }
}
