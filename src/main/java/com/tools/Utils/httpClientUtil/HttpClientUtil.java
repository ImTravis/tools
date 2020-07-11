package com.tools.Utils.httpClientUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * 版权 @Copyright: 2018 Waycom Inc. All rights reserved.
 * 项目名称：adminlte-manage
 * 文件名称： HttpClientUtil.java
 * 包名：com.soa.commons.httpClientUtil
 * 类说明：
 * 创建人：@author nick Long
 * 创建时间：2018-06-22-14:13
 * 修改人：nick Long
 * 修改时间：2018-06-22-14:13
 * 修改备注：
 */
@Slf4j
public class HttpClientUtil {
    public static void main(String[] args) {
        try {

//            Map<String, String> params = new HashMap<>();
//            params.put("randomStr", "789456");//调用方生成的随机数
//            String returlUrl = "http://192.168.2.121:8082/backUrl";
//            HttpClientUtil.doGet(returlUrl, params);

            String callback ="jQuery18307550803723522186_1584692865386";
            Map<String, String> paramsA = new HashMap<>();
            paramsA.put("callback", callback);//调用方生成的随机数
            paramsA.put("_", "1584692865522");//调用方生成的随机数

            String returlUrlA = "https://api.fund.eastmoney.com/Favor/Get";
            Map<String,String> header = new HashMap<>();
            String cookie ="Eastmoney_Fund=100038_750002_110007_005312_161017; Eastmoney_Fund_Transform=true; EMFUND1=null; EMFUND2=null; EMFUND3=null; EMFUND4=null; EMFUND5=null; EMFUND6=null; qgqp_b_id=46536cde6776fb567430a80b7fa7f32a; EMFUND0=null; EMFUND7=03-17%2014%3A48%3A39@%23%24%u5BCC%u56FD%u6CAA%u6DF1300%u6307%u6570%u589E%u5F3A@%23%24100038; EMFUND8=03-18%2014%3A40%3A57@%23%24%u94F6%u6CB3%u521B%u65B0%u6210%u957F%u6DF7%u5408@%23%24519674; EMFUND9=03-19 16:39:07@#$%u4E07%u5BB6%u7ECF%u6D4E%u65B0%u52A8%u80FD%u6DF7%u5408C@%23%24005312; st_pvi=39392333040446; st_sp=2020-03-17%2013%3A11%3A56; st_inirUrl=https%3A%2F%2Fwww.baidu.com%2Flink";
            header.put("Cookie",cookie);
            header.put("Accept-Encoding","gzip, deflate, br");
            header.put("Accept-Language","zh-CN,zh;q=0.9");
            header.put("Accept","*/*");
            header.put("Referer","http://favor.fund.eastmoney.com/");
            String str = HttpClientUtil.doGet(returlUrlA, paramsA,header);
            str = str.replace(callback,"").replace("(","").replace(")","");
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONObject data = (JSONObject)jsonObject.get("Data");
            JSONArray kfs = JSONArray.parseArray(JSONObject.toJSONString(data.get("KFS")));
            System.out.print("\n"+kfs);
            for(Object obj : kfs){
                JSONObject e = (JSONObject)JSONObject.toJSON(obj);
                System.out.print("\n票号:"+e.getString("FCODE")+",名称:"+e.getString("SHORTNAME")+",涨幅"+e.getString("gsz"));

            }
//            gsz
//                    SHORTNAME
//            FCODE
            System.out.print("\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 发送请求
     *
     * @param uri     请求的url，需要在前面包含网络协议(http 或者 https)
     * @param content 内容，为空：get，不为空：post
     * @return
     * @throws Exception
     */
    public static String send(String uri, String content) throws Exception {
        log.debug("uri:" + uri);
        log.debug("content:" + content);

        String protocol = uri.substring(0, 8).trim();
        protocol = protocol.toLowerCase();

        HttpClient httpClient = null;
        if (protocol.startsWith("https")) {
            httpClient = SSLClient.createSSLClientDefault();
        } else if (protocol.startsWith("http")) {
            // 创建HttpClientBuilder
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            // HttpClient
            httpClient = httpClientBuilder.build();

        } else {
            throw new Exception("不支持的网络协议");
        }

        HttpUriRequest httpUriRequest = null;
        if (StringUtils.isEmpty(content)) {
            // get请求
            httpUriRequest = new HttpGet(uri);
        } else {
            // post请求
            httpUriRequest = new HttpPost(uri);
            org.apache.http.HttpEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);
            ((HttpPost) httpUriRequest).setEntity(entity);
        }

        // 发送请求
        HttpResponse response = httpClient.execute(httpUriRequest);
        org.apache.http.HttpEntity output = response.getEntity();

        // 得到返回结果
        String ret = EntityUtils.toString(output, Consts.UTF_8);

        log.debug("status is " + response.getStatusLine());
        log.debug("output ==>" + ret);

        return ret;
    }

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        return resultString;
    }

    public static String doGet(String url, Map<String, String> param, Map<String, String> headers) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            for (Map.Entry<String, String> head : headers.entrySet()) {
                httpGet.addHeader(String.valueOf(head.getKey()),head.getValue());
            }

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


}
