package com.tools.taskQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author xcc.
 * @data 2019/3/5.
 * @time 15:58.
 */
public class TaskTest {

    public static void main(String args[]){

        BlockingQueue<TaskPeople> blockingQueue = new LinkedBlockingDeque<>();
        TaskPeople taskPeople = null;

        for(int k =0;k<5;k++){
            taskPeople = new TaskPeople();
            taskPeople.setName("C_00"+(k+1));
            taskPeople.setPhone("phone00"+(k+1));
            blockingQueue.add(taskPeople);
        }
//
//
//        TaskExecutor taskExecutor = new TaskExecutor(blockingQueue);
//        taskExecutor.start();

        TaskCompany taskCompany = new TaskCompany(blockingQueue,5);
        taskCompany.start();

        try{
            for(int k =6;k<11000;k++){
                taskPeople = new TaskPeople();
                taskPeople.setName("C_00"+(k+1));
                taskPeople.setPhone("phone00"+(k+1));
                taskCompany.addTask(taskPeople);
            }

        }catch (Exception e){

        }


    }
}
