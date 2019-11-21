package com.scs.blog.factory;

import com.scs.blog.dao.ArticleDao;
import com.scs.blog.dao.StudentDao;
import com.scs.blog.dao.UserDao;
import com.scs.blog.dao.impl.ArticleImpl;
import com.scs.blog.dao.impl.StudentDaoImpl;
import com.scs.blog.dao.impl.UserDaoImpl;


public class DaoFactory {
    public static StudentDao getStudentDaoInstance(){
        return new StudentDaoImpl();
    }
    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }
    public  static ArticleDao getArticleDaoInstance() {
        return new ArticleImpl();
    }

}
