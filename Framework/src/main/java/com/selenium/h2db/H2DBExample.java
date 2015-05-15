package com.selenium.h2db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class H2DBExample {
	
	 private static final String DB_DRIVER = "org.h2.Driver";
	 private static final String DB_CONNECTION = "jdbc:h2:~/Composite";
	 private static final String DB_USER = "sa";
	 private static final String DB_PASSWORD = "";
	
	
	public static Connection connectToH2DB()
	{
		
		 Connection dbConnection = null;
		try
		{
			   Class.forName(DB_DRIVER);
			   dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			   System.out.println("H2 DB Connected.");
		}
		catch(Exception e)
		{
			System.out.println("Error occured while Connecting to the H2DB...."+e.getMessage());
		}
		
		return dbConnection;
	}
	
	public static void createBookingReservationDataDataTable() throws SQLException
	{
		  PreparedStatement createPreparedStatement = null;
		  Connection con=null;
		try
		{
			
			 String CreateQuery = "CREATE TABLE BookingReservationData(id int primary key, fname varchar(255),lname varchar(255),age int,rvnum varchar(255))";
			 con=H2DBExample.connectToH2DB();
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
			con.close();
		}
	}
	
	
	public static int getMaximumValueFromBookingReservationData(Connection con) throws SQLException
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
			 
			 System.out.println("Maximum Id value ::"+maximum);
	          
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
	
	
	public static void insertRowIntoBookingReservationData(String fname,String lname,int age ) throws SQLException
	{
		PreparedStatement pstmt = null;
		Connection con=null;
		try
		{
			con=connectToH2DB();
			
			 int id=getMaximumValueFromBookingReservationData(con);
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
			con.close();
		}
		
	}
	
	public static void updateReservationNumber(String fName,String reservationNum) throws SQLException
	{
		PreparedStatement pstmt = null;
		Connection con=null;
		try
		{
			 con=H2DBExample.connectToH2DB();
			 String updateQuery = "UPDATE BookingReservationData SET rvnum='"+reservationNum+"' WHERE fName='"+fName+"'";
			 pstmt = con.prepareStatement(updateQuery);
			 pstmt.executeUpdate();
			 pstmt.close();
			  
	         System.out.println("'"+fName+"' Reservation Number updated with '"+reservationNum+"'");
	         pstmt.close();
	         con.commit();
		}
		catch(Exception ex)
		{ 
			System.out.println("Error occured while upating the ReservationNumber .."+ex.getMessage());
		}
		finally
		{
			pstmt.close();
			con.close();
		}
	}
	
	public static void printBookingReservationData()
	{
		 Connection con = null;
	     Statement stmt = null;
		try
		{
		  String selectQuery="select * from BookingReservationData";
		  con=H2DBExample.connectToH2DB();
		  stmt = con.createStatement();
		  ResultSet rs = stmt.executeQuery(selectQuery);
          
          while (rs.next()) {
        	   
        	     int sno=rs.getInt("id");
        	     String fName=rs.getString("fname");
        	     String lName=rs.getString("lname");
        	     int age=rs.getInt("age");
        	     String rvNum=rs.getString("rvnum");
        	   
        	    System.out.println("**********************");
        	    System.out.println(" Sno             ::"+sno);
        	    System.out.println(" First Name      ::"+fName);
        	    System.out.println(" Last  Name      ::"+lName);
        	    System.out.println(" Age             ::"+age);
        	    System.out.println(" Reservation Num ::"+rvNum);
        	    System.out.println("**********************");
        	   
             
          }
          stmt.close();
          con.commit();
			
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while reterving the values from the Table.."+ex.getMessage());
		}
		  
	}
	
	public static void printBookingReservationDataByName(String firstName)
	{
		 Connection con = null;
	     Statement stmt = null;
		try
		{
		  String selectQuery="select * from BookingReservationData WHERE fname='"+firstName+"'";
		  con=H2DBExample.connectToH2DB();
		  stmt = con.createStatement();
		  ResultSet rs = stmt.executeQuery(selectQuery);
          
          while (rs.next()) {
        	   
        	     int sno=rs.getInt("id");
        	     String fName=rs.getString("fname");
        	     String lName=rs.getString("lname");
        	     int age=rs.getInt("age");
        	     String rvNum=rs.getString("rvnum");
        	   
        	    System.out.println("**********************");
        	    System.out.println(" Sno             ::"+sno);
        	    System.out.println(" First Name      ::"+fName);
        	    System.out.println(" Last  Name      ::"+lName);
        	    System.out.println(" Age             ::"+age);
        	    System.out.println(" Reservation Num ::"+rvNum);
        	    System.out.println("**********************");
        	   
             
          }
          stmt.close();
          con.commit();
			
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while reterving the values from the Table.."+ex.getMessage());
		}
		  
	}
	
	public static void main(String[] args) throws SQLException {
		
		H2DBExample.createBookingReservationDataDataTable();
		
		
		H2DBExample.insertRowIntoBookingReservationData("Kumar","Andapally",27);
		H2DBExample.insertRowIntoBookingReservationData("Eswar","Genji",26);
		H2DBExample.printBookingReservationData();
		
		H2DBExample.updateReservationNumber("AB47874555","Kumar");
		H2DBExample.updateReservationNumber("AB47874556","Eswar");
		H2DBExample.printBookingReservationData();
		H2DBExample.printBookingReservationDataByName("Eswar");
		
	}

}
