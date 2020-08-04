package com.shuang.feign.impl;

import com.shuang.entity.Student;
import com.shuang.feign.StudentFeign;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFeignError implements StudentFeign {
    @Override
    public String index() {
        return null;
    }

    @Override
    public List<Student> findAll() {
        System.out.println("发生错误");
        return null;
    }

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void deleteById(Long id) {

    }



}
