package com.imooc.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��Ϣ���ṩ��
 * @author Administrator
 *
 */
public class AppProducer {
	
	
	@SuppressWarnings("unused")
	private static final String url="tcp://192.168.1.102:61616";
	@SuppressWarnings("unused")
	private static final String topicName="topic-test";
	
	public static void main(String[] args) throws JMSException {
		// 1���������ӹ���ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		//2������Connection
		Connection connection = connectionFactory.createConnection();
		//3����������
		connection.start();
		//4 �������Ự
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5������һ��Ŀ��
		Destination destination = session.createTopic(topicName);
		//6������һ��������
		MessageProducer producer = session.createProducer(destination);
		
		for(int i= 0;i<100;i++){
			//7��������Ϣ
			TextMessage textMessage = session.createTextMessage("test"+i);
			//8��������Ϣ
			producer.send(textMessage);
			
			System.out.println("������Ϣ"+textMessage.getText());
		}
		
		//9���ر�����
		connection.close();
		
	}
}
