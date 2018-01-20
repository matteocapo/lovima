package it.univaq.disim.se4s.mqttfunction;

import it.univaq.disim.se4s.callbackfunction.*;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class readFunction{
	
	static String topicin = "topic-in";
	static String topicout= "topic-out/";
	static String url = "tcp://localhost:1883";
	
	//genera il client con indirizzo e id 
	public static MqttClient connection() throws MqttException {
		MqttClient client= new MqttClient(url, MqttClient.generateClientId());
		client.connect();
	    return client;
	}
	
	//genera il topicin
	public static MqttTopic addTopicin(MqttClient client) {
		MqttTopic topic1 = client.getTopic(topicin);
		return topic1;
	}
	
	//genera il topicout
	public static MqttTopic addTopicout(MqttClient client) throws MqttException {
		MqttTopic topic1 = client.getTopic(topicout);
	    client.subscribe(topicout);
		return topic1;
	}
	
	//funzione per leggere la temperatura
	public static void readThemperature(String id) throws MqttException {
		MqttClient client = connection();
		MqttTopic topic = addTopicin(client);
		MqttTopic topicout = addTopicout(client);
		
		ReadThemperatureMqttCallBack mqttCall = new ReadThemperatureMqttCallBack(client);
		client.setCallback(mqttCall);
		
		String     message  = id+" readThemperature";
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	    }
	
	//funzione per leggere l'umidità
	public static void readHumidity(String id) throws MqttException {
		MqttClient client = connection();
		MqttTopic topic = addTopicin(client);
		MqttTopic topicout = addTopicout(client);
		
		ReadHumidityMqttCallBack mqttCall = new ReadHumidityMqttCallBack(client);
		client.setCallback(mqttCall);
		
		String     message  = id+" readHumidity";
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
	//funzione leggere la quantità di luce
	public static void readLight(String id) throws MqttException {
		MqttClient client = connection();
		MqttTopic topic = addTopicin(client);
		MqttTopic topicout = addTopicout(client);
		
		ReadLightMqttCallBack mqttCall = new ReadLightMqttCallBack(client);
		client.setCallback(mqttCall);
		
		String     message  = id+" readLight";
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
		}
	
	
	//funzione leggere se sono accese le ventole
	public static void readWindler(String id) throws MqttException {
		MqttClient client = connection();
		MqttTopic topic = addTopicin(client);
		MqttTopic topicout = addTopicout(client);
		
		ReadWindlerMqttCallBack mqttCall = new ReadWindlerMqttCallBack(client);
	    client.setCallback(mqttCall);
		
		String     message  = id+" readWindler";
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
		
	//funzione che informa se la la teca è polarizzata o meno 
	public static void readDisplay(String id) throws MqttException {
		MqttClient client = connection();
		MqttTopic topic = addTopicin(client);
		MqttTopic topicout = addTopicout(client);
		
		ReadDisplayMqttCallBack mqttCall = new ReadDisplayMqttCallBack(client);
		client.setCallback(mqttCall);
	
		String     message  = id+" readDisplay";
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
	//funzione che informa se l'allarme è attivo o no
	public static void readAlarm(String id) throws MqttException {
		MqttClient client = connection();
		MqttTopic topic = addTopicin(client);
		MqttTopic topicout = addTopicout(client);
		
		ReadAlarmMqttCallBack mqttCall = new ReadAlarmMqttCallBack(client);
		client.setCallback(mqttCall);
		
		String     message  = id+" readAlarm";
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
	
}