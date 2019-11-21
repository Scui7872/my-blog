package com.scs.blog.controller;

import com.scs.blog.util.ImageUtil;
import com.scs.blog.util.VerifyUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/api/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        生成验证码
        String code = VerifyUtil.generateRandomString(12);
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setAttribute("code",code);
//        生成图片
        BufferedImage img = ImageUtil.getImage(code,200,100);
//        设置resp的响应对象类型
        resp.setContentType("image/jpeg");

        OutputStream out = resp.getOutputStream();
        ImageIO.write(img,"jpg",out);
        out.close();
    }
}
