package it.univaq.disim.se4s.callbackfunction;

import it.univaq.disim.se4s.dbquery.*;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class WhoIsMqttCallBack implements MqttCallback {
	
	public boolean isMessageArrived;
	public MqttClient client;
	
	public WhoIsMqttCallBack(MqttClient client) {
		this.isMessageArrived = false;
		this.client = client;
	}
	
	
	public void connectionLost(Throwable throwable) {
		System.out.println("Connessione persa!");
		}

	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		
		isMessageArrived = true;
		String stringa = mqttMessage.toString();
		String [] splits = stringa.split("\\ ");
		String id = splits[0];
		String tipo = splits[1];
		String idAnimal = splits[2];
		DbInterface.setOnlineBoxes(id, tipo, idAnimal);
		
		try {
			client.setCallback(null);
			client.disconnect();
			client.close();
		}
		catch(Exception e) {
			
		}
		
	}

	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {	

		
	}
}


