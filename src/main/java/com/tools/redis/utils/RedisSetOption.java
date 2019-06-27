package com.tools.redis.utils;

import com.alibaba.fastjson.JSON;
import com.tools.redis.config.SimulatedData;
import com.tools.redis.entity.CheckDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author xcc.
 * @data 2019/2/1.
 * @time 11:51.
 * 集合（sets）
 */
@Component
public class RedisSetOption {
    private static final Logger logger = LoggerFactory.getLogger(RedisSetOption.class);

    @Autowired
    SimulatedData simulatedData;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    public void diffSets() {
        Map<String, Set<CheckDataInfo>> data = simulatedData.initData();


        Map<String, Set<String>> dataStr = objectToStr(data);

        redisTemplate.boundSetOps("localSetStr");
        redisTemplate.boundSetOps("outerSetStr");
        for (String s : dataStr.get("localSetStr")) {
            redisTemplate.opsForSet().add("localSetStr", s);
        }
        for (String s : dataStr.get("outerSetStr")) {
            redisTemplate.opsForSet().add("outerSetStr", s);
        }
        /**
         * 过期时间设置为2小时
         */
        redisTemplate.expire("localSetStr", 1000 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        redisTemplate.expire("outerSetStr", 1000 * 60 * 60 * 2, TimeUnit.MILLISECONDS);


        /**
         *  sdiff 方法的使用 求差集    求 2 次  其中可能订单号一样的不同数据也出现在其中 需要把订单号一样的数据捞出来
         */
        Set<Object> longPay = redisTemplate.opsForSet().difference("localSetStr", "outerSetStr");
        Set<Object> shortPay = redisTemplate.opsForSet().difference("outerSetStr", "localSetStr");

        List<CheckDataInfo> resultLong = new ArrayList<CheckDataInfo>();
        for (Object longObj : longPay) {
            CheckDataInfo longCoi = JSON.parseObject(longObj.toString(), CheckDataInfo.class);
            resultLong.add(longCoi);
        }

        List<CheckDataInfo> resultShort = new ArrayList<CheckDataInfo>();
        for (Object shortObj : shortPay) {
            CheckDataInfo shortCoi = JSON.parseObject(shortObj.toString(), CheckDataInfo.class);
            resultShort.add(shortCoi);
        }

        System.out.print("resultLong:" + resultLong.toString() + "\n");
        System.out.print("resultShort:" + resultShort.toString() + "\n");


        System.out.print("**************");
//        redisTemplate.delete("localSetStr");
//        redisTemplate.delete("outerSetStr");

    }

    public Map<String, Set<String>> objectToStr(Map<String, Set<CheckDataInfo>> data) {
        Map<String, Set<String>> result = new HashMap<String, Set<String>>();

        Set<CheckDataInfo> localSet = data.get("localSet");
        Set<String> localSetStr = new HashSet<String>();
        for (CheckDataInfo e : localSet) {
            localSetStr.add(JSON.toJSONString(e));
        }

        Set<CheckDataInfo> outerSet = data.get("outerSet");
        Set<String> outerSetStr = new HashSet<String>();
        for (CheckDataInfo e : outerSet) {
            outerSetStr.add(JSON.toJSONString(e));
        }
        result.put("localSetStr", localSetStr);
        result.put("outerSetStr", outerSetStr);

        return result;
    }

    /**
     * REDIS HASH - MAP
     */
    public void hashOption() {
        CheckDataInfo checkDataInfo = new CheckDataInfo();
        checkDataInfo.setOrderNo(System.currentTimeMillis() + "" + (int) ((Math.random() * 9 + 1)));
        checkDataInfo.setOrderTime(new Date());
        checkDataInfo.setOrderStaus(1);
        checkDataInfo.setPayment(new BigDecimal(10));
        redisUtils.hashSet("checkDataInfo", DataUtils.Obj2Map(checkDataInfo));
        System.out.print(redisUtils.hashGet("checkDataInfo")+"\n");
    }

}
