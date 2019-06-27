package com.tools.redis.config;

import com.tools.redis.entity.CheckDataInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author xcc.
 * @data 2019/2/1.
 * @time 12:02.
 * 模拟数据
 */
@Component
public class SimulatedData {

    public int array[] = {1,2,3,4};
    //平台拉的对账数据
    public Map<String,Set<CheckDataInfo>> initData(){
        Map<String,Set<CheckDataInfo>> result = new HashMap<String,Set<CheckDataInfo>>();
        Set<CheckDataInfo> publicData = new HashSet<>();
        publicData = creatCheckData(1);

        Set<CheckDataInfo> lcoalOrder = new HashSet<>();
        Set<CheckDataInfo> pingtaiOrder = new HashSet<>();

        lcoalOrder.addAll(publicData);
        pingtaiOrder.addAll(publicData);

        lcoalOrder.addAll(creatCheckData(1));//本地流水
        pingtaiOrder.addAll(creatCheckData(1));//第三方平台流水

        result.put("localSet",lcoalOrder);
        result.put("outerSet",pingtaiOrder);

        return result;

    }

    public Set<CheckDataInfo> creatCheckData(int index){
        Set<CheckDataInfo> result = new HashSet<CheckDataInfo>();
        CheckDataInfo checkDataInfo = null;
        BigDecimal money = null;
        for(int k=0;k<index;k++){
            checkDataInfo = new CheckDataInfo();
            checkDataInfo.setOrderNo(System.currentTimeMillis()+""+(int)((Math.random()*9+1)));
            checkDataInfo.setOrderTime(new Date());
            checkDataInfo.setOrderStaus(getOrderStatus());
            money = new BigDecimal(getOrderStatus()*10);
            checkDataInfo.setPayment(money);
            result.add(checkDataInfo);
        }
        return result;
    }


    /**
     * 随机订单状态 1,2,3,4
     * @return
     */
    public int getOrderStatus(){
        int ramdon = (int)((Math.random()*9+1)*10);
        int fix = (ramdon%4)+1;
        return fix;
    }
}
