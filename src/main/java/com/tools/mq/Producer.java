package com.tools.mq;

import org.apache.activemq.ActiveMQXAConnectionFactory;

import javax.jms.*;

/**
 * @author 作者 xcc:
 * @version 创建时间：
 * 类说明 :点对点
 */
public class Producer {
    private static final String userName = "admin";
    private static final String password = "admin";
    private static final String brokerURL = "tcp://192.168.0.142:61616";

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
        //7.创建发送者
        MessageProducer producer = session.createProducer(queue);
        //消息持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for(int i=1;i<=10;i++) {
            //6.消息对象
            TextMessage message = session.createTextMessage();
            message.setText("消息"+i);
            //8.发送消息
            producer.send(message);
        }
        //9.会话提交
//        session.commit();
//        session.close();

        //10.关闭连接
        connection.close();
    }
}
