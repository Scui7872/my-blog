package com.scs.blog.util;

import com.scs.blog.entity.Article;
import com.scs.blog.entity.Student;
import com.scs.blog.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JSoupSpider {

    public static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);

    public static List<Student> getStudents(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
        } catch (IOException e) {
            System.out.println("连接失败");
        }
        Elements divs = document.getElementsByClass("col-xs-8");
        List<Student> studentList = new ArrayList<>(divs.size());
        divs.forEach(div -> {
            Element wrapDiv = div.child(0);
            Element link = wrapDiv.child(0);
            Elements linkchildren = link.children();
            Student student = new Student();
            student.setUsername(linkchildren.get(1).text());
            student.setAvatar("https:" + linkchildren.get(0).attr("src"));
            student.setCreateTime(LocalDateTime.now());
            studentList.add(student);
        });
        return studentList;
    }

    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("用户信息网站连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(DataUtil.getMobile());
                user.setPassword(DataUtil.getPassword());
                user.setGender(DataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(DataUtil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                userList.add(user);
            });
        }
        return userList;
    }

    public static List<Article> getArticle(){
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);
        int i,j =0;
        for(  i = 0 ;i<=180 ;i++){
            try {
                document = Jsoup.connect("https://book.douban.com/review/best?start="+i).get();
            } catch (IOException e) {
                logger.error("豆瓣网站连接失败");
            }
            Elements divs = document.getElementsByClass("main review-item");
            divs.forEach(div ->{
                Element a = div.child(0);
                Element header = div.child(1);
                Element body = div.child(2);
                Article article = new Article();
                article.setCover(a.child(0).attr("src"));
                article.setNickname(header.child(1).text());
                if(header.children().size() == 4){
                    article.setPublishtime(Timestamp.valueOf(header.child(3).text()).toLocalDateTime());
                }else {
                    article.setPublishtime(Timestamp.valueOf(header.child(2).text()).toLocalDateTime());
                }
                article.setTitle(body.child(0).text());
                article.setContent(body.child(1).child(0).text());
                article.setIntro(body.child(1).child(0).text());
                article.setLikes(Integer.valueOf(body.child(3).child(0).child(1).text()));
                String comments = body.child(3).child(2).text();
                int length = comments.length();
                String number = comments.substring(0,length-2);
                article.setComments(Integer.valueOf(number));
                article.setDiamond(new Random().nextInt(100));
                articleList.add(article);
            });
            j++;
            i = 20*j;
        }
        return  articleList;
    }

}
