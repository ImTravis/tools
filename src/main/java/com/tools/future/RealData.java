package com.tools.future;

/**
 * @author xcc.
 * @data 2019/1/23.
 * @time 9:31.
 *
 * 业务处理返回真实数据
 */
public class RealData implements Data{
    private String result;
    
    public RealData (String queryStr){
        System.out.print("操作开始,请求参数："+queryStr+"\n");
        result = queryStr+"000\n";
        try{
                Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.print(queryStr+"操作完毕，获取结果"+"\n");

    }

    @Override
    public synchronized  String getRequest(){
        return result;
    }
}
