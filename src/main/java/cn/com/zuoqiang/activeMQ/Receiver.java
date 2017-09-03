package cn.com.zuoqiang.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;

public class Receiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("queue1");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        while (true) {
            TextMessage msg = (TextMessage) messageConsumer.receive();
            if (msg == null) {
                break;
            }
            System.out.println("收到的内容" + msg.getText());

        }
        if (connection != null) {
            connection.close();
        }

    }
}

