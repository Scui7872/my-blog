package com.scs.blog.util;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static BufferedImage getImage(String content,int width,int height) {
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        Color foreColor = new Color(26,160,52);
        Color bgColor = new Color(60,63,65);
        g.setBackground(bgColor);
        g.drawRect(0,0,200,100);
        g.setPaint(foreColor);
        Font font = new Font("微软雅黑",Font.BOLD,24);
        g.setFont(font);
        g.drawString(content,100,50);
        return img;
    }

    public static void main(String[] args)  throws IOException {
        String code = VerifyUtil.generateRandomString(12);
        BufferedImage img = ImageUtil.getImage(code,200,100);
        File file = new File("D:/code.jpg");
        ImageIO.write(img,"jpg",file);
    }
}
