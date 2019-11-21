package com.scs.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.blog.entity.Article;
import com.scs.blog.factory.ServiceFactory;
import com.scs.blog.service.ArticleService;
import com.scs.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/api/article")
public class ArticleController extends HttpServlet {
    ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articleList = articleService.listArticle();
        ResponseObject ro = null;
        if(resp.getStatus() ==200){
            ro = ResponseObject.success(200,"成功",articleList);
        }else{
            ro = ResponseObject.error(resp.getStatus(),"失败");
        }
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(ro));
        out.close();
    }

    @Override
    public void init() throws ServletException {
        logger.info("UserController初始化");
    }
}