package com.scs.blog.factory;

import com.scs.blog.service.ArticleService;
import com.scs.blog.service.UserService;
import com.scs.blog.service.impl.ArticleServiceImpl;
import com.scs.blog.service.impl.StudentServiceImpl;
import com.scs.blog.service.StudentService;
import com.scs.blog.service.impl.UserServiceImpl;

public class ServiceFactory {
    public static StudentService getStudentServiceInstance() {
        return new StudentServiceImpl();
    }

    public static UserService getUserServiceInstance() { return new UserServiceImpl();

    }

    public  static ArticleService getArticleServiceInstance() {
        return new ArticleServiceImpl();
    }
}
