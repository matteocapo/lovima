package it.univaq.disim.se4s.dbquery;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;


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
			try {
				connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/se4asdb","root", "");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Connessione non riuscita");				
			}
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
			  stmt.close(); 	  connection.close();

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
	  String query = "SELECT `alarm` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  alarm = rs.getBoolean("alarm");
			  }

			  System.out.println("query riuscita");

			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `display` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  display = rs.getBoolean("display");
			  }

			  System.out.println("query riuscita");

			  stmt.close(); 	  connection.close();
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
  
  public static Map<String, String> getAllValue(String id) throws SQLException {
	 
	  Map<String, String> diz = new HashMap<String, String>();
	  
	  Connection connection = connector();

	  String query = "SELECT * FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";

	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  
				  diz.put("idAnimals", rs.getString("idAnimals"));
				  diz.put("animalsN", rs.getString("animalsN"));
				  diz.put("temp", rs.getString("temp"));
				  diz.put("foodQnt", rs.getString("foodQnt"));
				  diz.put("waterQnt", rs.getString("waterQnt"));
				  diz.put("humidity", rs.getString("humidity"));
				  diz.put("alarm", rs.getString("alarm"));
				  diz.put("light", rs.getString("light"));
				  diz.put("display", rs.getString("display"));
				  diz.put("windler", rs.getString("windler"));
				  diz.put("type", rs.getString("type"));	
				  

			  }
			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
  
  public static Float getHumidity(String id) throws SQLException {
	  Connection connection = connector();

	  Float humidity = null;
	  String query = "SELECT `humidity` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  humidity = rs.getFloat("humidity");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `light` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  light = rs.getInt("light");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `temp` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  temp = rs.getFloat("temp");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `windler` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  windler = rs.getBoolean("windler");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `type` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  type = rs.getString("type");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `animalsN` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  number = rs.getInt("animalsN");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `foodQnt` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  number = rs.getDouble("foodQnt");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  String query = "SELECT `waterQnt` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  number = rs.getDouble("waterQnt");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  
	  String query = "SELECT DISTINCT `id_box` FROM `active_boxes`";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  onlinebxes.add(rs.getString("id_box"));
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  connection.close();
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
	  
	  Map <String, String> map = getAllValue(id);
	  
	  int val;
	  if(value) {
		   val = 1;
	  } else {
		   val = 0;
	  }
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`alarm`, `id_box`, `idAnimals`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `light`, `humidity`, `display`, `windler`, `type`) "
			  		+ "values('"+val+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("animalsN")+"', '"+map.get("temp")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("light")+"', '"+map.get("humidity")+"'"
			  				+ ", '"+map.get("display")+"', '"+map.get("windler")+"', '"+map.get("type")+"')");
			  System.out.println("query riuscita");
		  
			  stmt.close(); 	  connection.close();
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
	  
	  Map <String, String> map = getAllValue(id);
	  
	  int val;
	  if(value) {
		   val = 1;
	  } else {
		   val = 0;
	  }
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`display`, `id_box`, `idAnimals`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `light`, `humidity`, `alarm`, `windler`, `type`) "
				  		+ "values('"+val+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("animalsN")+"', '"+map.get("temp")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("light")+"', '"+map.get("humidity")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("windler")+"', '"+map.get("type")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`display`, `id_box`) values('"+val+"', '"+id+"')"); 
			  System.out.println("query riuscita");
			  stmt.close(); 	  connection.close();

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
	  
	  Map <String, String> map = getAllValue(id);

	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`humidity`, `id_box`, `idAnimals`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `light`, `display`, `alarm`, `windler`, `type`) "
				  		+ "values('"+value+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("animalsN")+"', '"+map.get("temp")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("light")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("windler")+"', '"+map.get("type")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`humidity`, `id_box`) values('"+value+"', '"+id+"')"); 
			  stmt.close(); 	  connection.close();
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
	  
	  Map <String, String> map = getAllValue(id);

	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`light`, `id_box`, `idAnimals`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `humidity`, `display`, `alarm`, `windler`, `type`) "
				  		+ "values('"+value+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("animalsN")+"', '"+map.get("temp")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("humidity")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("windler")+"', '"+map.get("type")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`light`, `id_box`) values('"+value+"', '"+id+"')"); 
			  stmt.close(); 	  connection.close();
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
	  
	  Map <String, String> map = getAllValue(id);

	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`temp`, `id_box`, `idAnimals`, `animalsN`, `light`, `foodQnt`, `waterQnt`, `humidity`, `display`, `alarm`, `windler`, `type`) "
				  		+ "values('"+value+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("animalsN")+"', '"+map.get("light")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("humidity")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("windler")+"', '"+map.get("type")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`temp`, `id_box`) values('"+value+"', '"+id+"')"); 
			  stmt.close(); 	  connection.close();
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
	  
	  Map <String, String> map = getAllValue(id);
	  
	  int val;
	  if(value) {
		   val = 1;
	  } else {
		   val = 0;
	  }
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`windler`, `id_box`, `idAnimals`, `animalsN`, `light`, `foodQnt`, `waterQnt`, `humidity`, `display`, `alarm`, `temp`, `type`) "
				  		+ "values('"+val+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("animalsN")+"', '"+map.get("light")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("humidity")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("temp")+"', '"+map.get("type")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`windler`, `id_box`) values('"+val+"', '"+id+"')"); 
			  stmt.close(); 	  connection.close();
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
	  
	  Map <String, String> map = getAllValue(id);
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`type`, `id_box`, `idAnimals`, `animalsN`, `light`, `foodQnt`, `waterQnt`, `humidity`, `display`, `alarm`, `temp`, `windler`) "
				  		+ "values('"+value+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("animalsN")+"', '"+map.get("light")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("humidity")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("temp")+"', '"+map.get("windler")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`type`, `id_box`) values('"+value+"', '"+id+"')"); 
			  stmt.close(); 	  connection.close();
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

	  Map <String, String> map = getAllValue(id);

	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`animalsN`, `id_box`, `idAnimals`, `type`, `light`, `foodQnt`, `waterQnt`, `humidity`, `display`, `alarm`, `temp`, `windler`) "
				  		+ "values('"+value+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("type")+"', '"+map.get("light")+"', '"+map.get("foodQnt")+"', '"+map.get("waterQnt")+"', '"+map.get("humidity")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("temp")+"', '"+map.get("windler")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`animalsN`, `id_box`) values('"+value+"', '"+id+"')"); 
			  stmt.close(); 	  connection.close();
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
	  
	  Map <String, String> map = getAllValue(id);
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`foodQnt`, `id_box`, `idAnimals`, `type`, `light`, `animalsN`, `waterQnt`, `humidity`, `display`, `alarm`, `temp`, `windler`) "
				  		+ "values('"+value+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("type")+"', '"+map.get("light")+"', '"+map.get("animalsN")+"', '"+map.get("waterQnt")+"', '"+map.get("humidity")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("temp")+"', '"+map.get("windler")+"')");
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`foodQnt`, `id_box`) values('"+value+"', '"+id+"')"); 
			  stmt.close(); 	  connection.close();
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

	  Map <String, String> map = getAllValue(id);

	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`waterQnt`, `id_box`, `idAnimals`, `type`, `light`, `animalsN`, `foodQnt`, `humidity`, `display`, `alarm`, `temp`, `windler`) "
				  		+ "values('"+value+"', '"+id+"', '"+map.get("idAnimals")+"', '"+map.get("type")+"', '"+map.get("light")+"', '"+map.get("animalsN")+"', '"+map.get("foodQnt")+"', '"+map.get("humidity")+"', '"+map.get("display")+"'"
				  				+ ", '"+map.get("alarm")+"', '"+map.get("temp")+"', '"+map.get("windler")+"')");
			  
			  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`waterQnt`, `id_box`) values('"+value+"', '"+id+"')"); 	
			  stmt.close(); 	  connection.close();
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
  public static void setOnlineBoxes(String id, String type, String idAnimal) throws SQLException {
	  Connection connection = connector();
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`active_boxes` (`id_box`) VALUES ('"+id+"')");
			  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes` (`id_box`, `type`, `idAnimals`) VALUES ('"+id+"', '"+type+"', '"+idAnimal+"')");
			  stmt.close(); 	  connection.close();
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
			  stmt.close(); 	  connection.close();

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
  
  public static int boolToInt(boolean value) {
	  if(value)
		  return 1;
	  else
		  return 0;
  }
  
  public static void setAll(String id, Float humidity, Float temp, int light, Boolean alarm, Boolean display,Boolean windler, String idanimal, String type) throws SQLException {
	  
		Connection connection = connector();

		  try {
			  Statement stmt = connection.createStatement();
			  try {
				  Double curWater = getWaterQnt(id);
				  Double curFood = getFoodQnt(id);
				  
				  stmt.executeUpdate("INSERT INTO `se4asdb`.`active_boxes`(`id_box`) VALUES ('"+id+"')");
				  stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`id_box`, `humidity`, `temp`, `light`, `alarm`, `display`, `windler`, `idAnimals`, `type`, `foodQnt`, `waterQnt`) "
					  		+ "values('"+id+"', '"+temp+"', '"+humidity+"', '"+light+"', '"+boolToInt(alarm)+"', '"+boolToInt(display)+"', '"+boolToInt(windler)+"', '"+idanimal+"', '"+type+"', '"+curFood+"', '"+curWater+"')");
				  //stmt.executeUpdate("INSERT INTO `se4asdb`.`boxes`(`foodQnt`, `id_box`) values('"+value+"', '"+id+"')"); 
				  stmt.close(); 	  connection.close();
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
  
  public static String getIdAnimal(String id) throws SQLException {
	  Connection connection = connector();

	  String idAnimal = null;
	  String query = "SELECT `idAnimals` FROM `boxes` WHERE `id_box`= '"+id+"' ORDER BY id DESC LIMIT 1";
	  
	  try {
		  Statement stmt = connection.createStatement();
		  try {
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while (rs.next()) {
				  idAnimal = rs.getString("idAnimals");
			  }

			  System.out.println("query riuscita");
			  
			  stmt.close(); 	  
			  connection.close();
			  return idAnimal;
			  
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
	  return idAnimal;
  }
	
}

  
