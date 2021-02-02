package com.shuang.entity;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddModelClass {

    private int id;
    private int model_id;
    private String model_name;
    private String model_content;
    private MultipartFile model_file;

}
