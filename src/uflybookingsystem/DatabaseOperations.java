/*
 * This class contains all the common operations that involve retreiving data from the database, 
 * saving data to the database or updating existing database data 
 * for all three tables (Location, Flight and Booking)
 */
package uflybookingsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import java.sql.Date;
import uflybookingsystem.BusinessObjects.Booking;
import uflybookingsystem.BusinessObjects.Flight;
import uflybookingsystem.BusinessObjects.Location;




public class DatabaseOperations {
    
    //method that gets all the information from the Location table
    public static ArrayList<Location> GetAllLocations(){
        ArrayList<Location> allLocations = new ArrayList<Location>();
        try(Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Location")){
             while (resultSet.next()) {
            	Location location = new Location();
            	location.setCity(resultSet.getString("City"));
            	location.setAirportCode(resultSet.getString("AirportCode"));
            	allLocations.add(location);
            }
        }
       catch(SQLException sqle){
            System.out.println(sqle.toString());
        } 
          return allLocations;
    }
    
    // this method returns all the data from the Flight table in the uFly database
    public static ArrayList<Flight> GetAllFlights(String departure, String destination){
        ArrayList<Flight> allFlights = new ArrayList<Flight>();
        try(Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Flight "
                    + "WHERE DepartureAirport = '" + departure + "' AND DestinationAirport = '"
                    + destination + "'")){
             while (resultSet.next()) {
            	Flight flight = new Flight();
            	flight.setFlightNumber(resultSet.getString("FlightNumber"));
                flight.setDepartureAirport(resultSet.getString("DepartureAirport"));
                flight.setDestinationAirport(resultSet.getString("DestinationAirport"));
                flight.setPrice(resultSet.getDouble("Price"));
                flight.setDateTime(resultSet.getString("DateTime"));
                flight.setPlane(resultSet.getString("Plane"));
                flight.setSeatsTaken(resultSet.getInt("SeatsTaken"));
            	allFlights.add(flight);
            }
        }
       catch(SQLException sqle){
            System.out.println(sqle.toString());
        } 
        return allFlights;
    }
    
    //this method obtains all the information from the Flight table based on the departure and destination airports as well as travel date
    public static ArrayList<Flight> GetAllFlightsForLocation(String departure, String destination, java.util.Date travelDate){
    ArrayList<Flight> Flights = new ArrayList<Flight>();
        try(Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Flight "
                    + "WHERE DepartureAirport = '" + departure + "' AND DestinationAirport = '"
                    + destination + "' AND DateTime = '" + travelDate + "'")){
             while (resultSet.next()) {
            	Flight flight = new Flight();
            	flight.setFlightNumber(resultSet.getString("FlightNumber"));
                flight.setDepartureAirport(resultSet.getString("DepartureAirport"));
                flight.setDestinationAirport(resultSet.getString("DestinationAirport"));
                flight.setPrice(resultSet.getDouble("Price"));
                flight.setDateTime(resultSet.getString("DateTime"));
                flight.setPlane(resultSet.getString("Plane"));
                flight.setSeatsTaken(resultSet.getInt("SeatsTaken"));
            	Flights.add(flight);
            }
        }
       catch(SQLException sqle){
            System.out.println(sqle.toString());
        } 
        return Flights;   
    }
    
    //this method adds booking passed as a parameter to the Booking table in the uFly database
    //note that Booking number is set as an incrementing field, so it doesn't need to be set
    public static void AddBooking(Booking booking){
    try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * from Booking")){
            
            resultSet.moveToInsertRow();
            resultSet.updateString("FlightNumber", booking.getFlightName());
            resultSet.updateDouble("Price",booking.getPrice());
            resultSet.updateString("CabinClass", booking.getCabinClass());
            resultSet.updateInt("Quantity", booking.getQuantity());
            resultSet.updateInt("Insurance", booking.getInsurance());
            resultSet.insertRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        } 
    }
    
    //this method obtains the flight based on the flightNumber parameter
    public static Flight getFlightByFLightNumber(String flightNumber){
    try(Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Flight "
                    + "WHERE  = '" + flightNumber + "'")){
             while (resultSet.next()) {
            	Flight flight = new Flight();
            	flight.setFlightNumber(resultSet.getString("FlightNumber"));
                flight.setDepartureAirport(resultSet.getString("DepartureAirport"));
                flight.setDestinationAirport(resultSet.getString("DestinationAirport"));
                flight.setPrice(resultSet.getDouble("Price"));
                flight.setDateTime(resultSet.getString("DateTime"));
                flight.setPlane(resultSet.getString("Plane"));
                flight.setSeatsTaken(resultSet.getInt("SeatsTaken"));
            	return flight;
            }
        }
       catch(SQLException sqle){
            System.out.println(sqle.toString());
        } 
        return null;
    }
    
     //this method obtains the flight based on the flightNumber parameter
   public static Location getLocationByAirportCode(String airportCode){
	try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Location "
                    + "WHERE AirportCode = '" + airportCode + "'")){
            
            while (resultSet.next()) {
            	Location location = new Location();
            	location.setCity(resultSet.getString("City"));
            	location.setAirportCode(resultSet.getString("AirportCode"));
            	return location;
            }
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }  
        return null;
    }
    
    //this method adds location passed as a parameter to the Location table in the uFly database
    public static void AddLocation(Location location){
	try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT City, AirportCode from Location")){
            
            resultSet.moveToInsertRow();
            resultSet.updateString("City", location.getCity());
            resultSet.updateString("AirportCode", location.getAirportCode());
            resultSet.insertRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        } 
    }
     
    //this method adds a flight passed as a parameter to the Flight table in the uFly database
    public static void AddFlight(Flight flight){
    try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * from Flight")){
           
            resultSet.moveToInsertRow();
            resultSet.updateString("FlightNumber",flight.getFlightNumber());
            resultSet.updateString("DepartureAirport", flight.getDepartureAirport());
            resultSet.updateString("DestinationAirport",flight.getDestinationAirport());
            resultSet.updateDouble("Price", flight.getPrice());
            resultSet.updateDate("DateTime", (Date)flight.getDateTime());
            resultSet.updateString("Plane", flight.getPlane());
            resultSet.updateInt("SeatsTaken", flight.getSeatsTaken());
            resultSet.insertRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }  
    }

    
    //this method updates the location to the one passed to it as a parameter where the airport codes are matching
    public static void UpdateLocation(Location location){
    try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * from Location "
                     + "WHERE AirportCode = '" + location.getAirportCode() + "'")){
        
            resultSet.absolute(1);
            resultSet.updateString("City", location.getCity());
            resultSet.updateString("AirportCode", location.getAirportCode());
            resultSet.updateRow();
        }
    catch(SQLException sqle){
            System.out.println(sqle.toString());
        }    
    }
    
    //this method updates the flight to the one passed to it as a parameter where the flight numbers are matching
    public static void UpdateFlight(Flight flight){
    try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * from Flight "
                    + "WHERE FlightNumber = '" + flight.getFlightNumber() + "'")){
           
            resultSet.absolute(1);
            resultSet.updateString("FlightNumber",flight.getFlightNumber());
            resultSet.updateString("DepartureAirport", flight.getDepartureAirport());
            resultSet.updateString("DestinationAirport",flight.getDestinationAirport());
            resultSet.updateDouble("Price", flight.getPrice());
            resultSet.updateDate("DateTime", (Date)flight.getDateTime());
            resultSet.updateString("Plane", flight.getPlane());
            resultSet.updateInt("SeatsTaken", flight.getSeatsTaken());
            resultSet.updateRow();
        }
        catch(SQLException sqle){
            System.out.println(sqle.toString());
        }    
    }
}
