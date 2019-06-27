package com.tools.taskQueue;

/**
 * @author xcc.
 * @data 2019/3/5.
 * @time 15:41.
 */

import lombok.Data;

/**
 * 任务
 * 抽象-需要取钱的人
 */
@Data
public class TaskPeople implements Task {

    private String name;

    private String phone;

    public TaskPeople() {
    }

    @Override
    public void drawMoney(String exeNum) {
        System.out.print("\n请["+this.name+"]到窗口["+exeNum+"]办理业务");
        try{
            Thread.sleep(100);

        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.print("\n"+this.name+"取钱发生特殊情况");
        }
        System.out.print("\n"+this.name+"取钱完毕");

    }
}
