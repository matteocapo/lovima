package it.univaq.disim.se4s.dbquery;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DbInterface {
  
  public static Connection connector() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			System.out.println("Non sono stati caricari i driver");
			e.printStackTrace();
			}
		
		System.out.println("Driver registrati");
		Connection connection = null;

		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/se4asdb","root", "root");
			} 
		catch (SQLException e) {
			System.out.println("Connessione non riuscita");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("Connessione riuscita");
			return connection;
		} else {
			System.out.println("Connessione fallita");
			return null;
		}
	}
  
  public static void querygenerica() {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate( "INSERT INTO `se4asdb`.`boxs` (`id`, `idMqtt`, `idAnimals`, `dateTime`, `animalsN`, `Temp`, `foodQnt`, `waterQnt`, `light`, `humidity`) VALUES ('2', '2', '2', '2018-01-02', '2', '24.5', '30', '40', '10', '15.4');" ); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  }
  
  public static Boolean getAlarm(String id) throws SQLException {
	  Connection connection = connector();

	  Boolean alarm = null;
	  String query = "SELECT `alarm` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  alarm = rs.getBoolean("alarm");
			  }

			  System.out.println("query riuscita");
			  return alarm;
			  
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
	  return alarm;
  }

  public static Boolean getDisplay(String id) throws SQLException {
	  Connection connection = connector();

	  Boolean display = null;
	  String query = "SELECT `display` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  display = rs.getBoolean("display");
			  }

			  System.out.println("query riuscita");
			  return display;
			  
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
	  return display;
  }
  
  public static Float getHumidity(String id) throws SQLException {
	  Connection connection = connector();

	  Float humidity = null;
	  String query = "SELECT `humidity` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  humidity = rs.getFloat("humidity");
			  }

			  System.out.println("query riuscita");
			  return humidity;
			  
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
	  return humidity;
  }
  
  public static Integer getLight(String id) throws SQLException {
	  Connection connection = connector();

	  Integer light = null;
	  String query = "SELECT `light` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  light = rs.getInt("light");
			  }

			  System.out.println("query riuscita");
			  return light;
			  
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
	  return light;
  }
  
  public static Float getThemperature(String id) throws SQLException {
	  Connection connection = connector();

	  Float temp = null;
	  String query = "SELECT `temp` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  temp = rs.getFloat("light");
			  }

			  System.out.println("query riuscita");
			  return temp;
			  
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
	  return temp;
  }

  public static Boolean getWindler(String id) throws SQLException {
	  Connection connection = connector();

	  Boolean windler = null;
	  String query = "SELECT `windler` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  windler = rs.getBoolean("display");
			  }

			  System.out.println("query riuscita");
			  return windler;
			  
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
	  return windler;
  }

  public static String getType(String id) throws SQLException {
	  Connection connection = connector();

	  String type = null;
	  String query = "SELECT `type` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  type = rs.getString("type");
			  }

			  System.out.println("query riuscita");
			  return type;
			  
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
	  return type;
  }

  public static Integer getAnimalsN(String id) throws SQLException {
	  Connection connection = connector();

	  Integer number = null;
	  String query = "SELECT `animalsN` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  number = rs.getInt("type");
			  }

			  System.out.println("query riuscita");
			  return number;
			  
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
	  return number;
  }

  public static Double getFoodQnt(String id) throws SQLException {
	  Connection connection = connector();

	  Double number = null;
	  String query = "SELECT `foodQnt` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  number = rs.getDouble("type");
			  }

			  System.out.println("query riuscita");
			  return number;
			  
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
	  return number;
  }

  public static Double getWaterQnt(String id) throws SQLException {
	  Connection connection = connector();

	  Double number = null;
	  String query = "SELECT `waterQnt` FROM `boxes` WHERE `id_box`= "+id;
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  number = rs.getDouble("type");
			  }

			  System.out.println("query riuscita");
			  return number;
			  
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
	  return number;
  }
  
  public static  List<String> getOnlibeBoxes() throws SQLException {
	  
	  Connection connection = connector();

	  List<String> onlinebxes = new ArrayList<String>();
	  
	  String query = "SELECT `id_box` FROM `active_boxes`";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  onlinebxes.add(rs.getString("id_box"));
			  }

			  System.out.println("query riuscita");
			  return onlinebxes;
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
	  return onlinebxes;
	
}
  
  
  public static void setAlarm(String id, Boolean value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `alarm` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close();
	  
  }
  
  public static void setDisplay(String id, Boolean value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `display` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }
  
  public static void setHumidity(String id, Float value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `humidity` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }
  
  public static void setLight(String id, Integer value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `light` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }
  
  public static void setThemperature(String id, Float value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `temp` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }
  
  public static void setWindler(String id, Boolean value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `windler` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }

  public static void setType(String id, String value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `type` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }

  public static void setAnimalsN(String id, String value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `animalsN` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }
  
  public static void setFoodQnt(String id, String value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `foodQnt` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }
  
  public static void setWaterQnt(String id, String value) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("UPDATE `boxes` SET `waterQnt` = "+value+"  WHERE `id_box` ="+id); 
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }

  /* questa funzione deve essere chiamata appena si collega un Iteca,
   * essa aggiunge una riga alla tabella active_boxes e una a boxes,
   * successivamente la tupla contenuta in boxes deve essere popolata di tutti gli altri paramentri
   */
  public static void setOnlineBoxes(String id, String type) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`active_boxes` (`id_box`, `type`) VALUES ('"+id+"', '"+type+"')");
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes` (`id_box`) VALUES ('"+id+"')");
			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
  }
  
  public static void deleteOnlineBox(String id) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("DELETE FROM `se4asdb`.`active_boxes` WHERE `active_boxes`.`id_box` = '"+id+"'");
			  stmt.executeUpdate("DELETE FROM `se4asdb`.`boxes` WHERE `boxes`.`id_box` = '"+id+"'");

			  System.out.println("query riuscita");
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.out.println("Errore query");
		}
	  }
	  catch (SQLException e) {
			  System.out.println("Errore Statment");
			  e.printStackTrace();
			  }	
	  connection.close(); 
	  
  }

  
}