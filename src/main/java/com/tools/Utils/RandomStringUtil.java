package com.tools.Utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * 随机生成汉子
 */
public class RandomStringUtil {
    // use apache common library
    private static String randomString() {
        return RandomStringUtils.random(2, 0x4e00, 0x9fa5, false, false);
    }

    private static final int BASE_RANDOM = 0x9fa5 - 0x4e00 + 1;
    private static Random random = new Random();

    public static char getRandomChar() {
        return (char) (0x4e00 + random.nextInt(BASE_RANDOM));
    }
    public static String getRandomStr() {
        return String.valueOf(getRandomChar());
    }

    public static String getRandomStr(int length){
        StringBuffer stringBuffer = new StringBuffer();
        for(int k=0;k<length;k++){
            stringBuffer.append(getRandomStr());
        }
        return stringBuffer.toString();

    }

    public static void main(String[] args) {
        System.out.println(getRandomStr());
    }
}
