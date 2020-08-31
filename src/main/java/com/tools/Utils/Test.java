package com.tools.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        List<BigDecimal> list = new ArrayList<>();
        BigDecimal bigDecimal;
        Random random = new Random();

        int add =0;
        int mul = 0;

        int addMore = 0;

        for(int k=0;k<100;k++){
            int randomnum = random.nextInt(366);
            if(randomnum%2==0){
                bigDecimal= new BigDecimal("0.015");
                list.add(bigDecimal);
                add++;
            }else{
                bigDecimal = new BigDecimal("-0.02");
                list.add(bigDecimal);
                mul++;
//
//                bigDecimal = new BigDecimal("-0.015");
//                list.add(bigDecimal);
//                mul++;
            }


        }

        BigDecimal total = new BigDecimal(10000);
        for(int k=0;k<100;k++){
            total = jisuan(list.get(k),total);
//            if((k+1)%10==0){
//                total = total.add(new BigDecimal(1000)).setScale(3);
//                System.out.println("追加1000");
//                addMore++;
//            }
        }
//        for(BigDecimal rate : list){
//            total = jisuan(rate,total);
//
//        }
        System.out.println("涨："+add+",跌:"+mul+",追加1000次数："+addMore);

    }

    public static BigDecimal jisuan(BigDecimal decimal,BigDecimal total){
        BigDecimal rate = new BigDecimal(1).add(decimal).setScale(3);
        BigDecimal result = total.multiply(rate).setScale(3,BigDecimal.ROUND_HALF_DOWN);
        System.out.println("result:"+result.toString()+"---rate:"+decimal.toString());
        return result;
    }
}
