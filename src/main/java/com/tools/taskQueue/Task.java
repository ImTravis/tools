package com.tools.taskQueue;

/**
 * @author xcc.
 * @data 2019/3/5.
 * @time 15:39.
 */

import com.tools.springDemo.MyScanner;
import org.springframework.stereotype.Component;

/**
 * 任务列表
 */

public interface Task {

    //取钱
    void drawMoney(String exeNum);
}
