package com.tools.Utils.httpClientUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
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

            Map<String, String> params = new HashMap<>();
            params.put("randomStr", "789456");//调用方生成的随机数
            String returlUrl = "http://192.168.2.121:8082/backUrl";
            HttpClientUtil.doGet(returlUrl, params);
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

}
