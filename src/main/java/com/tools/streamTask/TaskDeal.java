package com.tools.streamTask;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcc
 * @2019/12/14 15:13
 * 文件说明
 */
@Component
public class TaskDeal extends StreamTaskExec {


    public synchronized Object task(Object obj) {
        System.out.println(Thread.currentThread().getName()+"处理中..\n");
        try{
            Integer student = Integer.parseInt(String.valueOf(obj));
            if (student % 2 == 0) {
                return student;
            } else {
                return null;
            }
        }catch (Exception e){
            return null;
        }

    }

}
