package it.univaq.disim.se4s.mqttfunction;

import java.sql.SQLException;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;

import it.univaq.disim.se4s.dbquery.DbInterface;



public class App {
	
	public static void main(String[] args) throws MqttException, SQLException {
		DbInterface.getCSV("25");

	}

	
}
