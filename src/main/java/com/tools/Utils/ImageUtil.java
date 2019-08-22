package com.tools.Utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 作者 xcc:
 * @version 创建时间：
 * 类说明
 */
@Component
public class ImageUtil {

    private static String DEFAULT_PREVFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = false;//建议该值为false

    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 依据图片路径生成缩略图 </p>
     * @param file    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param prevfix    生成缩略图的前缀
     * @param force        是否强制依照宽高生成缩略图(假设为false，则生成最佳比例缩略图)
     */
    public void thumbnailImage(MultipartFile file, int w, int h, String prevfix, boolean force){
//        File imgFile = new File(imagePath);
        if(!file.isEmpty()){
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
//                if(imgFile.getName().indexOf(".") > -1) {
//                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
//                }// 类型和图片后缀所有小写，然后推断后缀是否合法
//                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
//                    System.out.print("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
//                    return ;
//                }
                suffix = "jpg";
                System.out.printf("target image's size, width:{}, height:{}.",w,h);
//                Image img = ImageIO.read(imgFile);
                Image img = ImageIO.read(file.getInputStream());
                if(!force){
                    // 依据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                            System.out.printf("change image's height, width:{}, height:{}.",w,h);
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                            System.out.printf("change image's width, width:{}, height:{}.",w,h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
//                String p = imgFile.getPath();
                String p="C:\\Users\\Administrator\\Desktop\\pic\\";
                // 将图片保存在原文件夹并加上前缀
                ImageIO.write(bi, suffix, new File(p + prevfix +"."+suffix));
                System.out.print("缩略图在原路径下生成成功");
            } catch (IOException e) {
                e.getStackTrace();
                System.out.print("generate thumbnail image failed.");
            }
        }else{
            System.out.print("the image is not exist.");
        }
    }

    public static void main(String[] args) {
//        new ImageUtil().thumbnailImage("C:\\Users\\Administrator\\Desktop\\pic\\155969873847.jpg", 100, 150,DEFAULT_PREVFIX,DEFAULT_FORCE);
    }
}
