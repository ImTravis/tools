package com.tools;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author Mr.Xue.
 * @program: tools
 * @create 2019-03-29 10:34
 * @des 描述：
 */
public class TestMain {

    public static void main(String[] args) {
//        Map map = new HashMap(4);
//        map.put("a","a");
//        map.put("b","b");
//        map.put("c","c");
//        map.put("d","d");
//        System.out.println(map.toString().length()+"\n");
//        int b = JSONUtils.toJSONString(map).length();
//        System.out.println(b+"\n");
//
//
//        Iterator iterator = map.entrySet().iterator();
//        while(iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry)iterator.next();
//            Object key = entry.getKey();
//
//            System.out.print("旧值："+entry.setValue("cc")+"\n");
//            System.out.print(key+"新值："+String.valueOf(entry.getValue())+"\n");
//        }

        String str="{\"stat\": \"SUC\", \"org\": \"[{STATUS=N, PNT_CODE=$, LVL=1, ORG_NAME=人社局, PTH=/103546, ORG_CODE=103546, INCOMP=103546, LEAF=0, COMPANY=1, FNAME=航院}, {STATUS=N, PNT_CODE=103546, LVL=2, ORG_NAME=崇川区人才市场, PTH=/103546/1812262257331aj84kwe, ORG_CODE=1812262257331aj84kwe, INCOMP=103546, LEAF=0, COMPANY=0, FNAME=航院/崇川区人才市场}, {STATUS=N, PNT_CODE=103546, LVL=2, ORG_NAME=测试机构1, PTH=/103546/181228130441xd6t9b6t, ORG_CODE=181228130441xd6t9b6t, INCOMP=103546, LEAF=1, COMPANY=0, FNAME=航院/测试机构1}, {STATUS=N, PNT_CODE=1812262257331aj84kwe, LVL=3, ORG_NAME=运输管理系, PTH=/103546/1812262257331aj84kwe/1812281306011djpm13n, ORG_CODE=1812281306011djpm13n, INCOMP=103546, LEAF=0, COMPANY=0, FNAME=航院/崇川区人才市场/运输管理系}, {STATUS=N, PNT_CODE=1812281306011djpm13n, LVL=4, ORG_NAME=物流管理, PTH=/103546/1812262257331aj84kwe/1812281306011djpm13n/181228130601irdsrkqw, ORG_CODE=181228130601irdsrkqw, INCOMP=103546, LEAF=0, COMPANY=0, FNAME=航院/崇川区人才市场/运输管理系/物流管理}, {STATUS=N, PNT_CODE=181228130601irdsrkqw, LVL=5, ORG_NAME=物流3172, PTH=/103546/181226225733j84kwe/181228130011djpm13n/181228130601irdsrkqw/181228130601o9ztzjn1, ORG_CODE=181228130601o9ztzjn1, INCOMP=103546, LEAF=1, COMPANY=0, FNAME=航院/崇川区人才市场/运输管理系/物流管理/物流3172}]\",  \"staff\": \"[{STATUS=N, SEX=M, STA_ID=421127199904013716, TYP=0, STA_NAME=张三, OFFICE_TEL=}, {STATUS=N, SEX=M, STA_ID=532131199805200920, TYP=0, STA_NAME=李四, OFFICE_TEL=}, {STATUS=N, SEX=M, STA_ID=cs01, TYP=0, STA_NAME=测试}, {STATUS=N, SEX=M, STA_ID=superman, TYP=0, STA_NAME=admin}]\",\"org_staff\": \"[{ORG_CODE=181228130601o9ztzjn1, ORG_PTH=/103546/1812262257331aj84kwe/1812281306011djpm13n/181228130601irdsrkqw/181228130601o9ztzjn1, STA_ID=421127199904013716, STA_NAME=张三, COMP_CODE=103546}, {ORG_CODE=181228130601o9ztzjn1, ORG_PTH=/103546/1812262257331aj84kwe/1812281306011djpm13n/181228130601irdsrkqw/181228130601o9ztzjn1, STA_ID=532131199805200920, STA_NAME=李四, COMP_CODE=103546}, {ORG_CODE=103546, ORG_PTH=/103546, STA_ID=cs01, STA_NAME=测试, COMP_CODE=103546}, {ORG_CODE=103546, ORG_PTH=/103546, STA_ID=superman, STA_NAME=admin, COMP_CODE=103546}]\"}";
        String aaa="{\"stat\": \"SUC\",\"org\": \"[{STATUS=N}]\"}";
        JSONObject obj = JSONObject.parseObject(str);
//        String staff = obj.getObject("staff",String.class);
        String staff = "[{name=hhh, age=12}, {name=ggg, age=45}]";

//        List<Map<String,Object>> ti = new ArrayList<Map<String,Object>>();
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("name","hhh");
//        map.put("age","12");
//        ti.add(map);
//
//        Map<String,Object> map2 = new HashMap<String,Object>();
//        map2.put("name","ggg");
//        map2.put("age","45");
//        ti.add(map2);
//
//        System.out.print(ti.toString());

        List es = JSONObject.parseArray(staff,List.class);
       List list =  obj.getObject("staff",List.class);
        System.out.print(obj);

    }
}
