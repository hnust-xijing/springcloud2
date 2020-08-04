package com.shuang.controller;

import com.shuang.entity.Student;
import com.shuang.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/student")

public class StudentHandler {

    @Autowired
    private StudentRepository studentRepository;

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return "当前的端口: "+this.port;
    }

    @GetMapping("/findAll")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @PostMapping("/findById/{id}")
    public void save(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PutMapping("/update")
    public void update(@RequestBody Student student) {
        studentRepository.update(student);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
    }
}
