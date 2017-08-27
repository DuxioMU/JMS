package com.imooc.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * ������
 * @author Administrator
 *
 */
public class AppConsumer {

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
		//6������һ��
		MessageConsumer consumer = session.createConsumer(destination);
		//7������һ��������
		consumer.setMessageListener(new MessageListener(){
			public void onMessage(Message message){
				TextMessage textMessage = (TextMessage)message;
				try {
					System.out.println("������Ϣ:"+textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		//8���ر����� ��Ҫ�ڼ����ر��ڹر�
		//connection.close();
		
	}
}
