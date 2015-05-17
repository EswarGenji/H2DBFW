package com.selenium.h2db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class H2DBBookingReservationData {
	
	 //private static String dbFielsPath="./DB/Composite";
	 //private static final String DB_CONNECTION = "jdbc:h2:file:"+dbFielsPath;
	
	 private static final String DB_DRIVER = "org.h2.Driver";
	 private static final String DB_CONNECTION = "jdbc:h2:mem:composite";
	 private static final String DB_USER = "sa";
	 private static final String DB_PASSWORD = "";
	
	 Connection con = null;
	 
	 public H2DBBookingReservationData()
	 {
		 connectToH2DB();
	 }
	
	public void connectToH2DB()
	{
		
		
		try
		{
			   Class.forName(DB_DRIVER);
			   con = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			   System.out.println("H2 DB Connected.");
		}
		catch(Exception e)
		{
			System.out.println("Error occured while Connecting to the H2DB...."+e.getMessage());
		}
		
		
	}
	
	public void createBookingReservationDataDataTable() throws SQLException
	{
		  PreparedStatement createPreparedStatement = null;
		  
		try
		{
			
			 String CreateQuery = "CREATE TABLE BookingReservationData(id int primary key, fname varchar(255),lname varchar(255),age int,rvnum varchar(255))";
			 createPreparedStatement = con.prepareStatement(CreateQuery);
	         createPreparedStatement.executeUpdate();
	         createPreparedStatement.close();
	         
	         System.out.println(" BookingReservationData Table created in the H2DB..");
			 
		}
		catch(Exception e)
		{
			System.out.println("Error occured while creating a Table in H2DB...."+e.getMessage());
		}
		finally
		{
			createPreparedStatement.close();
			
		}
	}
	
	
	public int getMaximumValueFromBookingReservationData() throws SQLException
	{
		int maximum=0;
		Statement stmt = null;
	
		try
		{
			 String selectQuery="SELECT MAX(id) AS id from BookingReservationData";
			 stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(selectQuery);
			 
			 if(rs.next())
				 maximum=rs.getInt("id");
			 
			// System.out.println("Maximum Id value ::"+maximum);
	          
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while getting the Maximum value from the BookingReservation Table..."+ex.getMessage());
		}
		finally
		{
			stmt.close();
			
		}
		
		return maximum;
	}
	
	
	public  void insertRowIntoBookingReservationData(String fname,String lname,int age ) throws SQLException
	{
		PreparedStatement pstmt = null;
		try
		{
			 int id=getMaximumValueFromBookingReservationData();
			 String InsertQuery = "INSERT INTO BookingReservationData" + "(id,fname,lname,age) values" + "(?,?,?,?)";
			 
			 
			 pstmt = con.prepareStatement(InsertQuery);
			 pstmt.setInt(1, ++id);
			 pstmt.setString(2,fname);
			 pstmt.setString(3,lname);
			 pstmt.setInt(4, age);
            
			 pstmt.executeUpdate();
			 
             System.out.println("'"+fname+"' Values inserted into the Booking Reservation Data Table..");
            
           
            
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while inserting the row into the Table.."+ex.getMessage());
		}
		finally
		{
			pstmt.close();
		}
		
	}
	
	public void updateReservationNumber(String fName,String reservationNum) throws SQLException
	{
		PreparedStatement pstmt = null;
		try
		{
			 String updateQuery = "UPDATE BookingReservationData SET rvnum='"+reservationNum+"' WHERE fName='"+fName+"'";
			 pstmt = con.prepareStatement(updateQuery);
			 pstmt.executeUpdate();
			 pstmt.close();
			  
	         System.out.println("'"+fName+"' Reservation Number updated with '"+reservationNum+"'");
	         pstmt.close();
	        
		}
		catch(Exception ex)
		{ 
			System.out.println("Error occured while upating the ReservationNumber .."+ex.getMessage());
		}
		finally
		{
			pstmt.close();
		}
	}
	
	public void printBookingReservationData() throws SQLException
	{
		 Statement stmt = null;
		try
		{
		  String selectQuery="select * from BookingReservationData";
		  stmt = con.createStatement();
		  ResultSet rs = stmt.executeQuery(selectQuery);
          
		  System.out.println("***********************************************************************");
  		  System.out.println("Sno   "+" First Name   "+"Last Name   "+" Age   "+"  Reservation Number");
          while (rs.next()) {
        	   
        	     int sno=rs.getInt("id");
        	     String fName=rs.getString("fname");
        	     String lName=rs.getString("lname");
        	     int age=rs.getInt("age");
        	     String rvNum=rs.getString("rvnum");
        	   
        	 /*   System.out.println("**********************");
        	    System.out.println(" Sno             ::"+sno);
        	    System.out.println(" First Name      ::"+fName);
        	    System.out.println(" Last  Name      ::"+lName);
        	    System.out.println(" Age             ::"+age);
        	    System.out.println(" Reservation Num ::"+rvNum);
        	    System.out.println("**********************");
        	   */
        	    
        		System.out.println(" "+sno+"  "+fName+"        "+lName+"       "+age+"       "+rvNum);
        		
        	    
             
          }
          System.out.println("***********************************************************************");
          
         	
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while reterving the values from the Table.."+ex.getMessage());
		}
		finally
		{
			stmt.close();
		}
		  
	}
	
	public BookingReservation getBookingReservationDataByName(String firstName,String lastName) throws SQLException
	{
		 Statement stmt = null;
		 BookingReservation reservation=new BookingReservation();
		try
		{
		  String selectQuery="select * from BookingReservationData WHERE fname='"+firstName+"' and lname='"+lastName+"'";
		  stmt = con.createStatement();
		  ResultSet rs = stmt.executeQuery(selectQuery);
          
          while (rs.next()) {
        	   
        	     int age=rs.getInt("age");
        	     String rvNum=rs.getString("rvnum");
        	     
        	     reservation.setFirstName(firstName);
        	     reservation.setLastName(lastName);
        	     reservation.setAge(age);
        	     reservation.setReservationNumber(rvNum);     
          }
          
          
			
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while reterving the values from the Table.."+ex.getMessage());
		}
		finally
		{
			stmt.close();
		}
		
		return reservation;
		  
	}
	
	

}
