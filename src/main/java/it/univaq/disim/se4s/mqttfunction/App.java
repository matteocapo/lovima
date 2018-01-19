package it.univaq.disim.se4s.mqttfunction;

import java.sql.SQLException;


import org.eclipse.paho.client.mqttv3.MqttException;



public class App {
	
	public static void main(String[] args) throws MqttException, SQLException {
		System.out.println("inizio");
		//DbInterface.setOnlineBoxes("1");
		//DbInterface.setOnlineBoxes("2");
		readFunction.readThemperature("1");
		readFunction.readThemperature("2");
		System.out.println("fine");

	}

	
}
