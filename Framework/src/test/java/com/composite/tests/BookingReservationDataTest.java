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
		data.insertRowIntoBookingReservationData("Kumar", "Anadapally", 25);
		data.insertRowIntoBookingReservationData("Vikas", "Narendarapu", 29);
		data.insertRowIntoBookingReservationData("Murthy", "Papolu", 28);
		data.insertRowIntoBookingReservationData("Swarrop", "Ondaranki", 28);
		
		
		
		//Updating the Reservation Number into Booking Reservation Data Table 
		data.updateReservationNumber("Eswar", "A784578S09");
		data.updateReservationNumber("Kumar", "A784578S58");
		
		
		//Getting the Booking Reservation Data by First Name and Last Name
		String firstName="Kumar";
		String lastName="Anadapally";
		
		BookingReservation reservation=new BookingReservation();
		reservation=data.getBookingReservationDataByName(firstName,lastName);
		
		System.out.println("***********************************************************************");
		System.out.println(" First Name   "+"Last Name   "+" Age   "+"  Reservation Number");
		System.out.println("  "+reservation.getFirstName()+"        "+reservation.getLastName()+"       "+reservation.getAge()+"       "+reservation.getReservationNumber());
		System.out.println("***********************************************************************");
	    
		data.printBookingReservationData();
		
	}
}
