package com.tools.taskQueue;

/**
 * @author xcc.
 * @data 2019/3/5.
 * @time 16:04.
 */

import com.tools.springDemo.MyScanner;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 专门执行任务的池
 * 抽象-银行等
 */
@MyScanner
public class TaskCompany {

    // 银行里面办事的人
    private BlockingQueue<TaskPeople> taskQueue;

    //执行的窗口-服务窗口人员
    private TaskExecutor[] taskExecutors;

    public TaskCompany(BlockingQueue<TaskPeople> taskQueue,int size) {
        this.taskQueue = taskQueue;
        this.taskExecutors = new TaskExecutor[size];

        TaskExecutor taskExecutor;
        for(int k = 0; k<size;k++ ){
            taskExecutor = new TaskExecutor("窗口编号00"+(k+1));
            taskExecutors[k] = taskExecutor;
        }
    }
    //银行开工
    public void start(){

        int size = taskExecutors.length;//有多少窗口
        for(int k =0;k<size;k++){
            taskExecutors[k].setTaskQueue(taskQueue);
            taskExecutors[k].start();
        }

    }
    //下班
    public void stop(){
        for(TaskExecutor taskExecutor : taskExecutors){
            taskExecutor.quit();
        }
    }

    /**
     * 添加任务
     * @param taskPeople
     */
    public void addTask(TaskPeople taskPeople){
        taskQueue.add(taskPeople);
    }
}
