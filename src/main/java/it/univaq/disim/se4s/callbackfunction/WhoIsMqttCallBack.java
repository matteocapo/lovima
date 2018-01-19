package it.univaq.disim.se4s.callbackfunction;

import it.univaq.disim.se4s.dbquery.*;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class WhoIsMqttCallBack implements MqttCallback {
	
	public void connectionLost(Throwable throwable) {
		System.out.println("Connessione persa!");
		}

	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		
		String stringa = mqttMessage.toString();
		String [] splits = stringa.split("\\ ");
		String id = splits[0];
		String tipo = splits[1];
		//String animalN = splits[2];
		DbInterface.setOnlineBoxes(id, tipo);
		
		}


	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {	
		
	}
}


