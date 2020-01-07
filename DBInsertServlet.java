package com.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/dbinsert")
public class DBInsertServlet extends HttpServlet {
  Connection connection = null ; 
  public void init() throws ServletException
  { 
	   try {
		   System.out.println("INIT invoked");
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   connection = DriverManager.getConnection("jdbc:oracle:thin:1521:orcl","hr","hr");
	   }
	   catch(ClassNotFoundException|SQLException e)
	   {
		   System.out.println(e);
	   }
	  
  }
  
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
	  int bookId = Integer.parseInt(req.getParameter("bookId"));
	  String bookName = req.getParameter("bookName");
	  insertDetails(bookId,bookName);
  }
  
  public void insertDetails(int bookId, String bookName) {
	  try {
		  Statement statement= connection.createStatement();
		  String query = "insert into book values (" + bookId + ",'" + bookName + "')";
		  int noofRowsInserted = statement.executeUpdate(query);
		  
		  if(noofRowsInserted ==1)
		  {
			  System.out.println("NO OF ROWS INSERTED"+ noofRowsInserted);
		  }
		  else
		  {
			  System.out.println("NO rows Inserted");
		  }
	  }
	  catch(SQLException e) {
		  System.out.println(e);
		  
		  
	  }
		  }
  }
  
 




/////////////////////////html document//////////////

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="dbinsert" method="post">
<input type ="text" placeholder= "Enter book Id" name="bookId">
<input type ="text" placeholder= "Enter book name" name="bookName">
<input type ="submit" value="Save Book">


</form>


</body>
</html
