package com.tools.future;

/**
 * @author xcc.
 * @data 2019/1/23.
 * @time 9:29.
 * future真是数据
 */
public class FutureData implements Data{
    private RealData realData;

    private boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notify();
    }
    @Override
    public synchronized  String getRequest(){
        while(!isReady){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return this.realData.getRequest();
    }
}
