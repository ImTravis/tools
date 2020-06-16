package com.tools;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Mr.Xue.
 * @program: tools
 * @create 2019-03-29 10:34
 * @des 描述：
 */
public class TestMain {

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("Chicken.java");
        try {
            File file = classPathResource.getFile();
            InputStream inputStream = classPathResource.getInputStream();
            ClassReader classReader = new ClassReader(inputStream);
            System.out.println("ew");
        } catch (IOException e) {
            e.printStackTrace();
        }



        String fqClassName = "com.tools.ToolsApplication";
        int lastDotIndex = fqClassName.lastIndexOf(46);
        System.out.println( lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "");

    }
}
