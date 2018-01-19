package it.univaq.disim.se4s.mqttfunction;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import it.univaq.disim.se4s.callbackfunction.WhoIsMqttCallBack;

public class whoIsFunction{
	
	static String topic = "topic-in";
	static String topicout = "topic-out/";
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
	
	public static MqttTopic addTopicout(MqttClient client) throws MqttException { 
		MqttTopic topic1 = client.getTopic(topicout);
	    client.subscribe(topicout);
		return topic1;
	}
	
	public void whoIs() throws MqttException {
		MqttClient client = connection();
	    String     message  ="whoIs";
	    MqttTopic topic = addTopic(client);
		MqttTopic topicout = addTopicout(client);
		client.setCallback(new WhoIsMqttCallBack());
	    MqttMessage     publication = new MqttMessage(message.getBytes());
	    topic.publish(publication);
	}
}
