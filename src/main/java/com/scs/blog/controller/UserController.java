package com.scs.blog.controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.blog.domain.UserDto;
import com.scs.blog.entity.User;
import com.scs.blog.factory.ServiceFactory;
import com.scs.blog.service.UserService;
import com.scs.blog.util.Message;
import com.scs.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {
      private static Logger logger = LoggerFactory.getLogger(UserController.class);
      private UserService userService = ServiceFactory.getUserServiceInstance();
    /**
     * 根据请求地址，取得最后的内容，结合请求方法来决定分发到不同的方法
     *
     * @param uri
     * @return
     */
    private String getPatten(String uri) {
        int len = "/api".length();
        return uri.substring(len);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/user/sign-in".equals(uri)) {
            signIn(req, resp);
        } else if ("/api/user/sign-up".equals(uri)) {
            signUp(req, resp);
        } else if ("/api/user/check".equals(uri)) {
            check(req, resp);
        }
    }
    private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("验证账号");
    }


    protected void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息：" + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(),UserDto.class);
        Map<String,Object> map = userService.signIn(userDto);
        String msg = (String) map.get("msg");
        ResponseObject ro;
        if(msg.equals(Message.SIGN_IN_SUCCESS)) {
            ro = ResponseObject.success(200,msg,map.get("data"));
        }else {
            ro = ResponseObject.success(200,msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/user".equals(uri)) {
            String page = req.getParameter("page");
            if (page != null) {
                getUsersByPage(req, resp);
            } else {
                getHotUsers(req, resp);
            }
        } else {
            getUser(req, resp);
        }
    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> userList = userService.listUsers();
        ResponseObject ro = null;
        if(resp.getStatus() ==200) {
            ro = ResponseObject.success(200,"成功",userList);
        } else {
            ro = ResponseObject.success(resp.getStatus(),"失败");
        }
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(ro));
        out.close();
        String info = req.getPathInfo().trim();
        //取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        resp.getWriter().println(id);
    }

    private void getHotUsers(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void getUsersByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }


    @Override
    public void init() throws ServletException {
        logger.info("UserController初始化");
    }

    protected void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        logger.info("注册用户信息"+stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(),UserDto.class);
        Map<String,Object> map = userService.signUp(userDto);
        String msg = (String)map.get("msg");
        ResponseObject ro;
        if(msg.equals("注册成功")){
            ro = ResponseObject.success(200,msg);
        } else {
            ro = ResponseObject.success(200,msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
}
