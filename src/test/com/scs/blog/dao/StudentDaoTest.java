package com.scs.blog.dao;

import com.scs.blog.entity.Student;
import com.scs.blog.factory.DaoFactory;
import com.scs.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private StudentDao studentDao = DaoFactory.getStudentDaoInstance();
    @Test
    public void insert() throws SQLException {
        Student student = new Student();
        student.setUsername("用户");
        student.setAvatar("1.jpg");
        student.setDescription("个人介绍");
        student.setCreateTime(LocalDateTime.now());
        int n = studentDao.insert(student);
        System.out.println("受影响行数为：" + n);
        assertEquals(1,n);
    }

    @Test
    public void selectAll() throws Exception {
        List<Student> studentList = studentDao.selectAll();
    }

    @Test
    public void batchInsert() throws SQLException {
        int[] n = studentDao.batchInsert(JSoupSpider.getStudents());
        assertEquals(24,n.length);
    }
}