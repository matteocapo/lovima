package it.univaq.disim.se4s.dbquery;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class DbAnimalsInterface {
  
  public static Connection connector() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Connection connection = null;

		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/se4asdb","root", "root");
			} 
		catch (SQLException e) {
			try {
				connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/se4asdb","root", "");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Errore Connessione");				
			}
		}

		if (connection != null) {
			return connection;
		} else {
			System.out.println("Errore Connessione");
			return null;
		}
	}
  
  
    
  public static int boolToInt(boolean value) {
	  if(value)
		  return 1;
	  else
		  return 0;
  }
  
  public static Map<String, String> getAllAnimalInfo(String id) throws SQLException {
		 
	  Map<String, String> diz = new HashMap<String, String>();
	  
	  Connection connection = connector();

	  String query = "SELECT * FROM `animals` WHERE `id_animal`= '"+id+"' ORDER BY id DESC LIMIT 1";

	  //System.err.println(id);
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  
				  diz.put("id_animal", rs.getString("id_animal"));
				  diz.put("type", rs.getString("type"));
				  diz.put("species", rs.getString("species"));
				  diz.put("sex", rs.getString("sex"));
				  diz.put("maxNPost", rs.getString("maxNPost"));
				  diz.put("food", rs.getString("food"));
				  diz.put("foodDoses", rs.getString("foodDoses"));
				  diz.put("minTemp", rs.getString("minTemp"));
				  diz.put("maxTemp", rs.getString("maxTemp"));
				  diz.put("minhum", rs.getString("minhum"));
				  diz.put("maxhum", rs.getString("maxhum"));

			  }
			  
			  stmt.close(); 	  
			  connection.close();
			  return diz;
			  
		  }
		  catch (Exception e) {
			  e.getStackTrace();
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close();
	  return diz;
  }
  
  public static Map<String, Float> tempRange(String id) throws SQLException {
	  
	  Map<String, Float> diz = new HashMap<String, Float>();

	  Connection connection = connector();

	  String query = "SELECT * FROM `animals` WHERE `id_animal`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  diz.put("minTemp", rs.getFloat("minTemp"));
				  diz.put("maxTemp", rs.getFloat("maxTemp"));
			  }

			  
			  stmt.close(); 	  
			  connection.close();
			  return diz;
			  
		  }
		  catch (Exception e) {
			  e.getStackTrace();
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close();
	  return diz;
  }
  
  public static int getFoodDoses(String id) throws SQLException {
	  int value = 0;
	  Connection connection = connector();
	  
	  String query = "SELECT * FROM `animals` WHERE `id_animal`= '"+id+"' ORDER BY id DESC LIMIT 1";

	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				 value = rs.getInt("foodDoses");
			  }

			  
			  stmt.close(); 	  
			  connection.close();
			  return value;
			  
		  }
		  catch (Exception e) {
			  e.getStackTrace();
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close();
	  return value;
	
  }
}

  
