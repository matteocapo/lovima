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

	  public static int boolToInt(boolean value) {
		  if(value)
			  return 1;
		  else
			  return 0;
	  }
	  
	  public static boolean stringToBool(String value) {
		  if(value.equals("1"))
			  return true;
		  else
			  return false;
	  }
	
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		
		String stringa = mqttMessage.toString();
		String [] splits = stringa.split("\\ ");
		
		String id = splits[0];
		try {
		Float humidity = Float.parseFloat(splits[1]);
		Float temp =  Float.parseFloat(splits[2]);
		int light = Integer.parseInt(splits[3]);
		Boolean alarm = stringToBool(splits[4]);
		Boolean display = stringToBool(splits[5]);
		Boolean windler = stringToBool(splits[6]);
		String idanimal = splits[7];
		String type = splits[8];
		DbInterface.setAll(id, humidity, temp, light, alarm, display, windler, idanimal, type);

		}
		catch(Exception e) {
			DbInterface.deleteOnlineBox(id);
		}
		
	}


	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {	
		
	}

}


