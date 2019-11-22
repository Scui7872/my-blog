package com.scs.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;


@MultipartConfig
@WebServlet(urlPatterns = "/api/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        Collection<Part> parts = req.getParts();
        long maxFilesize = 1024 * 1024 * 8;
        for(Part part :parts) {
            long filesize = part.getSize();
            System.out.println(filesize);
            if(filesize>maxFilesize) {
                req.setAttribute("msg","文件太大，上传失败");
            } else if(filesize ==0) {
                req.setAttribute("msg","可文件上传");
            } else {
                String name = part.getSubmittedFileName();
                System.out.println(name);
                int index = name.indexOf(".");
                String suffix = name.substring(index);
                String filename = UUID.randomUUID().toString().replace("-","") + suffix;
                String path = req.getSession().getServletContext().getRealPath("") + date;
                System.out.println(path);

                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                part.write(file.getPath() + "/" + filename);
                resp.setContentType("image/jpg");
                req.setAttribute("msg","上传成功！");
                req.setAttribute("url","https://localhost:8080/" + date + "/" + filename);
                System.out.println(file.getName());
                System.out.println(file.getAbsolutePath() + "/" + filename);
            }
        }
//        Part part = req.getPart("filename");
//        String name = part.getSubmittedFileName();
//        System.out.println(name);
//        String path = req.getSession().getServletContext().getRealPath("");
//        System.out.println(path);

//        part.write(path + name);
//        System.out.println(path + name);

        req.getRequestDispatcher("/upload.jsp").forward(req,resp);
    }
}
