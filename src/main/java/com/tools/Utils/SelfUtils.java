package com.tools.Utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mr.Xue.
 * @program: employment
 * @create 2019-03-15 15:04
 * @des 描述：
 */
public class SelfUtils {

    public static String SALT = "zk_";


    /**
     * 订单生成规则
     * 获取订单号 salt+时间戳+随机数（6位）
     *
     * @param type
     * @return
     */
    public static String getOrderIdByUUId(int type) {

        String random = getRandom(6);
        SimpleDateFormat aDate = new SimpleDateFormat("yyyymmddHHmmss");
        long now = System.currentTimeMillis();
        String time = aDate.format(now);
        String orderNo = time + random;
        switch (type) {

            case 1:
                //支付宝
                orderNo = SALT + orderNo;
                break;
            case 2:
                //微信
                orderNo = SALT + orderNo;
                break;
        }
        return orderNo;
    }


    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int number = 0;
        for (int i = 0; i < length; i++) {
            number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 判断字符串 不能为 null ,空，“null”
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else {
            String smg = String.valueOf(obj).trim();
            if (StringUtils.isEmpty(smg)) {
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * 判断字符串 不能为 null ,空，“null”
     *
     * @param objs
     * @return
     */
    public static boolean isBlank(Object ...objs) {
        boolean isEmpty = false;
        for(Object obj : objs){
            if (obj == null) {
                isEmpty = true;
            } else {
                String smg = String.valueOf(obj).trim();
                if (StringUtils.isEmpty(smg)) {
                    isEmpty = true;
                } else {
                    isEmpty = false;
                }
            }
        }
        return isEmpty;

    }

    /**
     * 判断字符串 不能为 null ,空，“null”
     *
     * @param args
     * @return
     */
    public static boolean isEmpty(Object ... args) {
        boolean isEmpty = false;
        for(Object arg : args){
            if (arg == null) {
                isEmpty = true;
                break;
            } else {
                String smg = String.valueOf(arg).trim();
                if (StringUtils.isEmpty(smg)) {
                    isEmpty = true;
                    break;
                } else {
                    isEmpty = false;
                    continue;
                }
            }
        }
        return isEmpty;
    }

    /**
     * 获取当前时间前N天的，所有时间的List
     * @return
     */
    public static List<String> getTimeList(int days){
        List<String> times = new ArrayList<>();
        Date now = new Date();
        Date startDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int k =days;k>=0;k--){
            startDate = DateUtils.addDays(now, -k);
            times.add(sdf.format(startDate));
        }
        return times;
    }

    /**
     * 获取当前时间前N天的，所有时间的List
     * @return
     */
    public static Map<String,String> getStartEnd(int days){
        Map<String,String> result = new HashMap<>();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date endDate  = DateUtils.addDays(now, 0);
        Date startDate  = DateUtils.addDays(now, -30);

        result.put("start",sdf.format(startDate));
        result.put("end",sdf.format(endDate));
        return result;
    }

    /**
     * 获取指定位数的随机数
     *
     * @param index
     * @return
     */
    public static String getRandom(int index) {
        StringBuffer flag = null;
        for (int i = 0; i <= index; i++) {
            String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
            Random rand = new Random();
            flag = new StringBuffer();
            for (int j = 0; j < index; j++) {
                flag.append(sources.charAt(rand.nextInt(9)) + "");
            }
        }
        return flag.toString();
    }

    public static void main(String[] args){
        System.out.print(getRandom(2));
//        LocalDateTime now = LocalDateTime.now();
//        now = now.minus(30, ChronoUnit.DAYS);
//        System.out.println(now.toString());

//        System.out.println( getTimeList(30));
    }
}
