package com;

import java.sql.*;

public class Complaint
{
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
 
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/complaints", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readComplaints()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Electricity Account Number</th><th>Complaint Type</th><th>Contact Details</th>"+"<th>Attachments</th><th>Update</th><th>Remove</th></tr>";
 
			String query = "select * from complaints";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
 
			// iterate through the rows in the result set
			while (rs.next())
			{
				String complaintID = Integer.toString(rs.getInt("complaintID"));
				String eNumber = rs.getString("eNumber"); 
				String cType = rs.getString("cType");
				String cDetails =  rs.getString("cDetails");
				String attachments = rs.getString("attachments");
 
				// Add into the html table
				output += "<tr><td><input id='hidComplaintIDUpdate'name='hidComplaintIDUpdate'type='hidden' value='" + complaintID+ "'>" + eNumber + "</td>";
				output += "<td>" + cType + "</td>";
				output += "<td>" + cDetails + "</td>";
				output += "<td>" + attachments + "</td>";
 
				// buttons
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'data-complaintid='"+ complaintID + "'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-complaintid='"+ complaintID + "'> </td></tr>";
			}
 
			con.close();
 
			// Complete the html table
			output += "</table>";
		}
 
		catch (Exception e)
		{
			output = "Error while reading the complaints.";
			System.err.println(e.getMessage());
		}
 
		return output;
	}
	
	public String insertComplaint(String num, String type,String details, String attach)
    {
		String output = "";

		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
 
			// create a prepared statement
			String query = " insert into complaints(`complaintID`,`eNumber`,`cType`,`cDetails`,`attachments`)"+ " values (?, ?, ?, ?, ?)";
		 
			PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, num);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, details);
			preparedStmt.setString(5, attach);
		 
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newComplaints = readComplaints();
			output = "{\"status\":\"success\", \"data\": \"" +newComplaints+ "\"}";
		 }
		
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the complaint.\"}";
			 System.err.println(e.getMessage());
		 }
		
		 return output;
		 
		 }
	
		 public String updateComplaint(String ID, String num, String type, String details, String attach)
		 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for updating.";
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE complaints SET eNumber=?,cType=?,cDetails=?,attachments=? WHERE complaintID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				 // binding values
				 preparedStmt.setString(1, num);
				 preparedStmt.setString(2, type);
				 preparedStmt.setString(3, details);
				 preparedStmt.setString(4, attach);
				 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
		
				 String newComplaints = readComplaints();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newComplaints + "\"}";
		 }
			 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the complaint.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		return output;
	}
		
	public String deleteComplaint (String complaintID)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting.";
			 }
		 
			 // create a prepared statement
			 String query = "delete from complaints where complaintID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(complaintID));
		 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newComplaints = readComplaints();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newComplaints + "\"}";
		 }
		 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the complaint.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
		 }
	}
		 