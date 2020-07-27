//package com.tools.mqtt;
//
//import com.tools.springDemo.AAA;
//import org.springframework.beans.factory.xml.XmlBeanFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//
//
//public class MqttTest {
//    public static void main(String[] args) {
//
//
//        FileSystemResource classPathResource = new FileSystemResource("classpath:application.yml");
//        XmlBeanFactory factory2 = new XmlBeanFactory(classPathResource);
//
////        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
////        ac.scan("com.tools");
////        ac.refresh();
//        MqttProducer aaa = (MqttProducer)factory2.getBean("mqttProducer");
//        aaa.consumerMessage("hello my name is travis");
//    }
//}
