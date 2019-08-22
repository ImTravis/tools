package com.tools.Utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class ThumbnailMaker {

    /**
     *jz 2018-9-26
     * 生成缩略图
     */

    /**
     * @param im
     * @Description: 取得图片对象
     * @date 2017年5月7日10:48:27
     */
    public BufferedImage zoomImage(BufferedImage im, int toWidth, int toHeight) {
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        return result;
    }

    /**
     * @Description: 取得图片对象
     * @date 2017年5月7日10:48:27
     */
    public BufferedImage getImageList(MultipartFile file, String[] type) throws IOException {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        for (String s : type) {
            map.put(s, true);
        }
        BufferedImage imageList = null;

        try {
            //if(file.length() != 0 && map.get(getExtension(file.getName())) != null ){ 在之前已做了图片类型判断，此处省略
            if (!file.isEmpty()) {
                imageList = ImageIO.read(file.getInputStream());
            }
        } catch (Exception e) {
            imageList = null;
        }

        return imageList;
    }

    /**
     * 把图片写到磁盘上
     *
     * @param im
     * @param path     eg: C://home// 图片写入的文件夹地址
     * @param fileName DCM1987.jpg  写入图片的名字
     * @date 2017年5月7日10:48:27
     */

    public boolean writeToDisk(BufferedImage im, String path, String fileName) {
        File f = new File(path + fileName);
        String fileType = getExtension(fileName);
        if (fileType == null)
            return false;
        try {
            ImageIO.write(im, fileType, f);
            im.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @Description: 生成图片
     * @date 2017年5月7日10:48:27
     */
    public boolean writeHighQuality(String path, BufferedImage im, String fileFullPath) throws IOException {

        try {




            ByteArrayOutputStream os  = new ByteArrayOutputStream();


            //ImageIO.write(im, /*"GIF"*/ path.substring(path.indexOf('.') + 1) /* format desired */, new File(fileFullPath + "/" + path ));
            ImageIO.write(im, /*"GIF"*/ path.substring(path.indexOf('.') + 1) /* format desired */, os );

            InputStream is = new ByteArrayInputStream(os.toByteArray());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            System.out.print("");

            //近JPEG编码
//            newimage.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @Description: 取文件名的后缀
     * @date 2017年5月7日10:48:27
     */
    public String getExtension(String fileName) {
        try {
            return fileName.split("\\.")[fileName.split("\\.").length - 1];
        } catch (Exception e) {
            return null;
        }
    }

    public static String abbreviationPic(MultipartFile file, int w, int h) throws IOException {


        String newFileName = "xcc.jpg";
        String newPath ="C:\\Users\\Administrator\\Desktop\\pic\\";

        ThumbnailMaker r = new ThumbnailMaker();

        BufferedImage imageList = r.getImageList(file, new String[]{"jpg", "png", "gif"});
        r.writeHighQuality(newFileName, r.zoomImage(imageList, w, h), newPath);

        return newPath + newFileName;
    }


}
