package it.univaq.disim.se4s.callbackfunction;

import it.univaq.disim.se4s.dbquery.*;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class ReadAlarmMqttCallBack implements MqttCallback {
	
	public void connectionLost(Throwable throwable) {
		System.out.println("Connessione persa!");
		}

	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {	
		
		String stringa = mqttMessage.toString();
		String [] splits = stringa.split("\\ ");
		String id = splits[0];
		Boolean value = Boolean.parseBoolean(splits[1]);
		DbInterface.setAlarm(id, value);

		}


	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {	
		
	}

}


