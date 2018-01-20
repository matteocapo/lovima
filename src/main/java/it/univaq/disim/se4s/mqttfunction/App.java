package it.univaq.disim.se4s.mqttfunction;

import java.sql.SQLException;


import org.eclipse.paho.client.mqttv3.MqttException;

import it.univaq.disim.se4s.dbquery.DbInterface;



public class App {
	
	public static void main(String[] args) throws MqttException, SQLException {
		//System.out.println("inizio");
		//DbInterface.setOnlineBoxes("1", "T");
		//DbInterface.setOnlineBoxes("2", "T");
		//readFunction.readThemperature("1");
		//readFunction.readThemperature("2");
		//DbInterface.setLight("1", 237);
		//System.err.println(DbInterface.getLight("1"));
		//System.out.println("fine");
		//whoIsFunction.whoIs();
		readFunction.readAlarm("1");
	}

	
}
