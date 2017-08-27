package com.imooc.jms.queue;

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
	//private static final String url="tcp://192.168.1.100:61616";
	//����activemq��Ⱥ
	private static final String url=
			"failover:(tcp://192.168.1.100:61616,"
			+ "tcp://192.168.1.100:61617,"
			+ "tcp://192.168.1.100:61618)"
			+ "?randomize=true";
	
	
	@SuppressWarnings("unused")
	private static final String queueName="queue-test";
	
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
		Destination destination = session.createQueue(queueName);
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
