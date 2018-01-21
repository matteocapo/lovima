package it.univaq.disim.se4s.callbackfunction;

import it.univaq.disim.se4s.dbquery.*;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class ReadAllMqttCallBack implements MqttCallback {
	
	public MqttClient client;
	public ReadAllMqttCallBack(MqttClient client) {
		this.client = client;
	}
	
	public void connectionLost(Throwable throwable) {
		System.out.println("Connessione persa!");
		}

	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		
		String stringa = mqttMessage.toString();
		String [] splits = stringa.split("\\ ");
		
		String id = splits[0];
		Float humidity = Float.parseFloat(splits[1]);
		Float temp =  Float.parseFloat(splits[2]);
		int light = Integer.parseInt(splits[3]);
		Boolean alarm = Boolean.parseBoolean(splits[4]);
		Boolean display = Boolean.parseBoolean(splits[5]);
		Boolean windler = Boolean.parseBoolean(splits[6]);
		String idanimal = splits[7];
		String type = splits[8];
		DbInterface.setAll(id, humidity, temp, light, alarm, display, windler, idanimal, type);
		client.disconnect();
		
	}


	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {	
		
	}

}

