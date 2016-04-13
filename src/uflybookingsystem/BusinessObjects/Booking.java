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
public class Booking {
    
    private int bookingNumber;
    private String flightNumber;
    private double price;
    private String cabinClass;
    private int quantity;
    private int insurance;
    
    public void setBookingNumber(int bookingNumber){
        this.bookingNumber = bookingNumber;
    }
    
    public int getBookingNumber(){
        return this.bookingNumber;
    }
    
    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }
    
    public String getFlightName(){
        return this.flightNumber;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void setCabinClass(String cabinClass){
        this.cabinClass = cabinClass;
    }
    
    public String getCabinClass(){
        return this.cabinClass;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public void setInsurance(int insurance){
        this.insurance = insurance;
    }
    
    public int getInsurance(){
        return this.insurance;
    }
    
}
