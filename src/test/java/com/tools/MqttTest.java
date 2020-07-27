package com.tools.mqtt;

import com.tools.ToolsApplication;
import com.tools.springDemo.AAA;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MqttTest {

    @Autowired
    MqttProducer mqttProducer;


    @Test
    public void produce(){
       Destination destination = new ActiveMQQueue("out.queue");
        mqttProducer.send(destination,"out.queue");
    }

}
