package com.shuang.feign;

import com.shuang.entity.Student;
import com.shuang.feign.impl.StudentFeignError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "student", fallback = StudentFeignError.class)
public interface StudentFeign {



    @GetMapping("/student/index")
    public String index();

    @GetMapping("/student/findAll")
    public List<Student> findAll();

    @GetMapping("/student/findById/{id}")
    public Student findById(@PathVariable("id")Long id);

    @PostMapping("/student/save")
    public void save(@RequestBody Student student);

    @PutMapping("/student/update")
    public void update(@RequestBody Student student);

    @DeleteMapping("/student/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id);

}
