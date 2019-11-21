package com.scs.blog.service;

import com.scs.blog.entity.Student;
import com.scs.blog.factory.ServiceFactory;
import com.scs.blog.service.StudentService;
import org.junit.Test;

import java.util.List;

public class StudentServiceTest {
    private StudentService studentService = ServiceFactory.getStudentServiceInstance();

    @Test
    public void listStudents() {
        List<Student> studentList = studentService.listStudents();
        studentList.forEach(System.out::println);
    }
}
