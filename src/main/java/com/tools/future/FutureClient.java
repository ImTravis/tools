package com.tools.future;

/**
 * @author xcc.
 * @data 2019/1/23.
 * @time 9:27.
 * future模式请求端
 */
public class FutureClient {

    public Data request(final String queryStr){
        final  FutureData futureData = new FutureData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

    public static void main(String args[]){

        FutureClient client = new FutureClient();
        Data data = client.request("tim");
        Data data2 = client.request("jerry");

        String b = data2.getRequest();
        String a = data.getRequest();
        System.out.print(b);
        System.out.print(a);
    }
}
