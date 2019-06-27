package com.tools.taskQueue;

/**
 * @author xcc.
 * @data 2019/3/5.
 * @time 15:48.
 */

import java.util.concurrent.BlockingQueue;

/**
 * 执行任务的窗口
 * 抽象-银行串口的服务人员
 */
public class TaskExecutor extends Thread {

    // 在窗口等待办事的任务。
    private BlockingQueue<TaskPeople> taskQueue;

    //窗口是否开启
    private boolean isRunning = true;

    //窗口编号
    private String exeNum;

    public TaskExecutor(BlockingQueue<TaskPeople> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public TaskExecutor(String exeNum) {
        this.exeNum = exeNum;
    }

    /**
     * 下班了
     */
    public void quit(){
        System.out.print("\n窗口"+exeNum+"已经下班");
        this.isRunning = false;
        interrupt();
    }

    @Override
    public void run(){
        while(isRunning){
            TaskPeople taskPeople;
            try{
                taskPeople = taskQueue.take();
            }catch (InterruptedException e){
                //如果此时已经下班 那就关了
                if(!isRunning){
                    interrupt();
                    break;
                }
                //没有下班那就等（说不定窗口的服务人员上厕所了）
                continue;

            }
            taskPeople.drawMoney(exeNum);
        }

    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public String getExeNum() {
        return exeNum;
    }

    public void setExeNum(String exeNum) {
        this.exeNum = exeNum;
    }

    public BlockingQueue<TaskPeople> getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(BlockingQueue<TaskPeople> taskQueue) {
        this.taskQueue = taskQueue;
    }
}
