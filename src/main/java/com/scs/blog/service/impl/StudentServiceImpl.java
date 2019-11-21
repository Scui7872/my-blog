package com.scs.blog.service.impl;

import com.scs.blog.dao.StudentDao;
import com.scs.blog.entity.Student;
import com.scs.blog.factory.DaoFactory;
import com.scs.blog.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = DaoFactory.getStudentDaoInstance();
    @Override
    public List<Student> listStudents() {
      List<Student> studentList = null;
      try {
          studentList = studentDao.selectAll();
      } catch (SQLException e) {
          System.out.println("查询所有学生出现异常");
      }
      return studentList;
    }
}
