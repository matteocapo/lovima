package it.univaq.disim.se4s.mqttfunction;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class setFunction{
	
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
	
	//funzione per accendere o spegnere la ventola
	public static void setWindler(String id, Boolean value) throws MqttException {
		MqttClient client = connection();
	    String     message  = id +" setWindler "+ value.toString();
	    MqttTopic topic = addTopic(client);
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	    }
	
	//funzione per chiudere o aprire la finestra
	public static void setDisplay(String id ,Boolean value) throws MqttException {
		MqttClient client = connection();
	    String     message  = id +" setDisplay "+ value.toString();
	    MqttTopic topic = addTopic(client);
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
	//funzione per accendere o spegnere l'allarme
	public static void setAlarm(String id, Boolean value) throws MqttException {
		MqttClient client = connection();
	    String     message  = id +" setAlarm "+ value.toString();
	    MqttTopic topic = addTopic(client);
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
}