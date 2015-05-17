package com.composite.tests;

import java.sql.SQLException;

import com.selenium.h2db.BookingReservation;
import com.selenium.h2db.H2DBBookingReservationData;

public class BookingReservationDataTest {
	
	
	
public static void main(String[] args) throws SQLException {
		
		
		H2DBBookingReservationData data=new H2DBBookingReservationData();
		data.createBookingReservationDataDataTable();
		
		
		//Inserting data into Booking Reservation Data Table
		data.insertRowIntoBookingReservationData("Eswar", "Genji", 26);
		data.insertRowIntoBookingReservationData("Kumar", "Anada", 25);
		data.insertRowIntoBookingReservationData("Vikas", "Naren", 29);
		data.insertRowIntoBookingReservationData("Murty", "Papol", 28);
		data.insertRowIntoBookingReservationData("Raman", "Ondar", 28);
		
		
		
		//Updating the Reservation Number into Booking Reservation Data Table 
		data.updateReservationNumber("Eswar", "A784578S09");
		data.updateReservationNumber("Kumar", "A784578S58");
		
		
		//Getting the Booking Reservation Data by First Name and Laast Name
		String firstName="Kumar";
		String lastName="Anada";
		
		BookingReservation reservation=new BookingReservation();
		reservation=data.getBookingReservationDataByName(firstName,lastName);
		
		System.out.println("***********************************************************************");
		System.out.println(" First Name   "+"Last Name   "+" Age   "+"  Reservation Number");
		System.out.println("  "+reservation.getFirstName()+"        "+reservation.getLastName()+"       "+reservation.getAge()+"       "+reservation.getReservationNumber());
		System.out.println("***********************************************************************");
	    
		data.printBookingReservationData();
		
	}
}
