package com.selenium.h2db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class H2DBExample {
	
	 private static final String DB_DRIVER = "org.h2.Driver";
	 private static final String DB_CONNECTION = "jdbc:h2:~/test";
	 private static final String DB_USER = "sa";
	 private static final String DB_PASSWORD = "";
	
	
	public static Connection connectToH2DB()
	{
		 Connection dbConnection = null;
		try
		{
			   Class.forName(DB_DRIVER);
			   System.out.println("Driver loaded the successfully..");
			   dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			   System.out.println("H2 DB Connected.");
		}
		catch(Exception e)
		{
			System.out.println("Error occured while Connecting to the H2DB...."+e.getMessage());
		}
		
		return dbConnection;
	}
	
	public static void createDataTable()
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
	         
	         System.out.println("Table created in the H2DB..");
			 
		}
		catch(Exception e)
		{
			System.out.println("Error occured while creating a Table in H2DB...."+e.getMessage());
		}
	}
	
	public static void insertRow(int id,String fname,String lname,int age )
	{
		PreparedStatement insertPreparedStatement = null;
		Connection con=null;
		try
		{
			 String InsertQuery = "INSERT INTO BookingReservationData" + "(id,fname,lname,age) values" + "(?,?,?,?)";
			 con=H2DBExample.connectToH2DB();
			 
		    insertPreparedStatement = con.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, id);
            insertPreparedStatement.setString(2,fname);
            insertPreparedStatement.setString(3,lname);
            insertPreparedStatement.setInt(4, age);
            
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            
            System.out.println("Row inserted into the Table ...");
            
            insertPreparedStatement.close();
            con.commit();
            
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while inserting the row into the Table.."+ex.getMessage());
		}
		
		
	}
	
	public static void updateReservationNumber(String reservationNum,String fName)
	{
		PreparedStatement insertPreparedStatement = null;
		Connection con=null;
		try
		{
			 con=H2DBExample.connectToH2DB();
			 String updateQuery = "UPDATE BookingReservationData SET rvnum='"+reservationNum+"' WHERE fName='"+fName+"'";
			  insertPreparedStatement = con.prepareStatement(updateQuery);
			  insertPreparedStatement.executeUpdate();
	          insertPreparedStatement.close();
			  
	            System.out.println("Updated the Table ...");
	            
	            insertPreparedStatement.close();
	            con.commit();
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while upating the ReservationNumber .."+ex.getMessage());
		}
	}
	
	public static void printRowValues()
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
              System.out.println(rs.getInt("id")+" "+rs.getString("fname")+" "+rs.getString("lname")+" "+rs.getInt("age")+" "+rs.getString("rvnum"));
          }
          stmt.close();
          con.commit();
			
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while reterving the values from the Table.."+ex.getMessage());
		}
		  
	}
	
	public static void main(String[] args) {
		
		//H2DBExample db=new H2DBExample();
		//H2DBExample.connectToH2DB();
		//H2DBExample.createDataTable();
		//H2DBExample.insertRow(1,"Kumar","Andapally",27);
		//H2DBExample.insertRow(2,"Eswar","Genji",26);
		
	   
		
		H2DBExample.updateReservationNumber("AB47874555","Kumar");
		H2DBExample.updateReservationNumber("AB47874556","Eswar");
		H2DBExample.printRowValues();
	}

}
