package com.shuang.controller;

import com.shuang.entity.Student;
import com.shuang.feign.StudentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentHandler {
    @Autowired
    private StudentFeign studentFeign;


    @GetMapping("/index")
    public String index(){
        return studentFeign.index();
    }

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentFeign.findAll();
    }
}
