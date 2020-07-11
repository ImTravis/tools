package com.tools.controller;

import com.tools.Utils.ImageUtil;
import com.tools.Utils.SpringUtil;
import com.tools.Utils.ThumbnailMaker;
import com.tools.Utils.httpClientUtil.HttpClientUtil;
import com.tools.common.db.Book;
import com.tools.common.db.Urlset;
import com.tools.redis.scene.distributedLock.RedisService;
import com.tools.redis.scene.distributedLock.ThreadRedis;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * @author xcc.
 * @data 2019/2/19.
 * @time 18:05.
 */
@RestController
public class TestController {

    @Autowired
    Book book;
    @Autowired
    ImageUtil imageUtil;
    /**
     * 支付服务回调地址-返回预下单参数
     * @param request
     */
    @RequestMapping(value = "/backUrl")
    public void backUrl(HttpServletRequest request){
        String money = request.getParameter("money");

        String goodInfo = request.getParameter("goodInfo");
        String randomStr = request.getParameter("randomStr");
        String shopper = request.getParameter("shopper");
        String yzState = request.getParameter("yzState");
        String tradeType = request.getParameter("tradeType");
        String status = request.getParameter("status");
        String orderNo = request.getParameter("orderNo");
        String finishTime = request.getParameter("finishTime");


        System.out.print("\nmoney:"+money+",goodInfo:"+goodInfo+",orderNo="+orderNo);
        //TODO 返回注册页面
    }

    /**
     * 支付服务回调地址，返回randomStr
     */
    @RequestMapping(value = "/registInfo")
    public void registInfo(){
        Map<String,String> registInfo = new HashMap<String,String>();
        registInfo.put("name","xcc");
        registInfo.put("phone","18260038010");
        registInfo.put("randomStr","4564612315688");
        HttpClientUtil.doPost("http://uipay.ughen.cn/yzPay/regist",registInfo);
    }


    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/testRedis")
    public void testRedis() {
        for (int i = 0; i < 500; i++) {
            ThreadRedis threadA = new ThreadRedis(redisService);
            threadA.start();
        }
    }


    /**
     * 压缩字符串，以文件形式返回
     * @param request
     * @param response
     */
    @RequestMapping(value = "ServletDemo02")
    public void ServletDemo02(HttpServletRequest request, HttpServletResponse response){
        try{
            String data = "我是asadfdsf我是asadfdsf我是asadfds"
                    + "f我是asadfdsf我是asadfdsf"
                    + "我是asadfdsf我是asadfdsf我是"
                    + "asadfdsf我是asadfdsf我是asadfd"
                    + "sf我是asadfdsf我是asadfdsf我是as"
                    + "adfdsf我是asadfdsf我是asadfdsf我是"
                    + "asadfdsf我是asadfdsf我是asadfdsf"
                    + "我是asadfdsf我是asadfdsf我是asadfd"
                    + "sf我是asadfdsf我是asadfdsf我是asa"
                    + "dfdsf我是asadfdsf我是asadfdsf我是a"
                    + "sadfdsf我是asadfdsf我是asadfdsf我是"
                    + "asadfdsf我是asadfdsf我是asadfdsf我是"
                    + "asadfdsf我是asadfdsf我是asadfdsf我是"
                    + "asadfdsf我是asadfdsf我是asadfdsf我是"
                    + "asadfdsf我是asadfdsf我是asadfdsf我是a"
                    + "sadfdsf我是asadfdsf我是asadfdsf";

            System.out.println("压缩前大小：" + data.getBytes().length);
            /**
             * 开始压缩
             */
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            GZIPOutputStream gout = new GZIPOutputStream(bout);
            gout.write(data.getBytes());
            gout.close();
            //得到压缩数据
            byte[] b = bout.toByteArray();
            response.setHeader("content-disposition", "attachment;filename="+"1.txt");
            response.setHeader("Content-Encoding", "gzip");
            response.setHeader("Content-Length", b.length + "");
            response.getOutputStream().write(b);

        }catch (Exception e){
            e.getStackTrace();
        }

    }

    @RequestMapping(value = "getVerify")
    public String getVerify(Map map ,HttpServletRequest request){
        Map<String,String> result = new HashMap<String,String>();

        return "2";

    }

    @RequestMapping(value = "/sitemap", produces = {"application/xml;charset=UTF-8"}, method = RequestMethod.GET)
    private Urlset getSitemapXml() {
        Urlset urlset = new Urlset();
        Urlset.Url url = new Urlset.Url();
        url.setLoc("kjlhuujjkhjk");
        url.setLastmod("323");
        url.setChangefreq("3232");
        url.setPriority("3232");
        urlset.getUrl().add(url);
        return urlset;
    }

    @RequestMapping(value = "/getSingle", method = RequestMethod.GET)
    private String getSingle() {
        book.setTime("ds");

        Book booka = SpringUtil.getBean(Book.class);
        booka.setName("bookA");

        Book bookb = SpringUtil.getBean(Book.class);
        System.out.print(bookb);

        Book bookc = new Book();
        bookc.getName();
        System.out.print(bookc);
        return "";
    }


    /**
     * 缩略
     *
     * @return
     */
    @PostMapping("smallPic")
    public String recogCarNumOpenBarrier(@RequestParam(value="file") MultipartFile file) {

        imageUtil.thumbnailImage(file,100, 150,"aa_",false);
        return "";
    }

    @ResponseBody
    @RequestMapping("/uploadAbbreviationLogo")
    public String uploadAbbreviationLogo(@RequestParam(value="file") MultipartFile file) throws IOException, FileUploadException {

        try {

            String slPath = ThumbnailMaker.abbreviationPic(file, 200, 100);

            System.out.print(slPath);


        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

}
