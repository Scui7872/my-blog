package com.scs.blog.dao;

import com.scs.blog.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    int insert(Student student) throws SQLException;
    int[] batchInsert(List<Student> studentList) throws SQLException;
    List<Student> selectAll() throws SQLException;
}
