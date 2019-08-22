package com.tools.mq;

import org.apache.activemq.ActiveMQXAConnectionFactory;

import javax.jms.*;

/**
 * @author 作者 xcc:
 * @version 创建时间：
 * 类说明
 */
public class Consumer {
    private static final String userName = ActiveMQXAConnectionFactory.DEFAULT_USER;
    private static final String password = ActiveMQXAConnectionFactory.DEFAULT_PASSWORD;
    private static final String brokerURL = "tcp://192.168.0.78:61616";

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂类
        ConnectionFactory factory = new ActiveMQXAConnectionFactory(userName, password, brokerURL);
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.启动连接
        connection.start();

        //4.创建会话对象session(事务transacted为true,参数2不生效)
        //acknowledgeMode:
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);

        //5.目的地
        Queue queue = session.createQueue("BX-AutoInPark");

        //6.接收消息对象
        MessageConsumer consumer = session.createConsumer(queue);

        //7.通过监听器接收消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                //获取消息
                try {
                    String msg = textMessage.getText();
                    System.out.println(msg);
                } catch (JMSException e) {
                }
            }
        });

    }
}
