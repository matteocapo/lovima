package it.univaq.disim.se4s.mqttfunction;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class setFunctionOnOff{
	
	static String topic = "topic-in";
	static String url = "tcp://localhost:1883";
	
	//genera il client con indirizzo e id 
	public static MqttClient connection() throws MqttException {
		MqttClient client= new MqttClient(url, MqttClient.generateClientId());
		client.connect();
	    return client;
	}
	
	//genera il topic
	public static MqttTopic addTopic(MqttClient client) {
		MqttTopic topic1 = client.getTopic(topic);
		return topic1;
	}
	
	//funzione per accendere e la ventola
	public static void setWindlerOn(String id) throws MqttException {
		MqttClient 	client = connection();
	    String 		message  = id +" setWindler"+" true";
	    MqttTopic 	topic = addTopic(client);
	    MqttMessage publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
	//funzione per spegnere e la ventola
	public static void setWindlerOff(String id) throws MqttException {
		MqttClient 	client = connection();
	    String 		message  = id +" setWindler"+" false";
	    MqttTopic 	topic = addTopic(client);
	    MqttMessage 	publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
	//funzione per aprire la finestra
	public static void setDisplayOpen(String id) throws MqttException {
		MqttClient 	client = connection();
	    String 		message  = id +" setDisplay"+" true";
	    MqttTopic 	topic = addTopic(client);
	    MqttMessage 	publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
	//funzione per aprire la finestra
	public static void setDisplayClose(String id) throws MqttException {
		MqttClient 	client = connection();
	    String 		message  = id +" setDisplay"+" false";
	    MqttTopic 	topic = addTopic(client);
	    MqttMessage 	publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
			
	}
	
	//funzione per accendere l'allarme
	public static void setAlarmOn(String id) throws MqttException {
		MqttClient 	client = connection();
	    String 		message  = id +" setAlarm"+" true";
	    MqttTopic 	topic = addTopic(client);
	    MqttMessage 	publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
	//funzione per spegnere l'allarme
	public static void setAlarmOff(String id) throws MqttException {
		MqttClient 	client = connection();
		String 		message  = id +" setAlarm"+" false";
		MqttTopic 	topic = addTopic(client);
		MqttMessage 	publication = new MqttMessage(message.getBytes());
		topic.publish(publication);
		}
}