/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem.BusinessObjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Raja
 */
public class Flight {
    
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private double price;
    private Date dateTime;
    private String plane;
    private int seatsTaken;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }
    
    public String getFlightNumber(){
        return this.flightNumber;
    }
    
    public void setDepartureAirport(String departureAirport ){
        this.departureAirport= departureAirport;
    }
    
    public String getDepartureAirport(){
        return this.departureAirport;
    }
    
    public void setDestinationAirport(String destinationAirport){
        this.destinationAirport = destinationAirport;
    }
    
    public String getDestinationAirport(){
        return this.destinationAirport;
    }
    
    public void setPrice(Double price ){
        this.price = price;
    }
    
    public Double getPrice(){
        return this.price;
    }
    
    public void setDateTime(String dateTime){
       try{
           this.dateTime = dateFormat.parse(dateTime);
       }
       catch(ParseException pe){
           System.out.println("Check the date string passed in");
       }  
    }
    
    public Date getDateTime(){
        return this.dateTime ;
    }
    
    public void setPlane(String plane){
        this.plane = plane;
    }
    
    public String getPlane(){
        return this.plane;
    }
    
    public void setSeatsTaken(int seatsTaken){
        this.seatsTaken = seatsTaken;
    }
    
    public int getSeatsTaken(){
        return this.seatsTaken;
    }
    
    @Override public String toString(){
        return this.dateTime.toString();
    }
    
}
