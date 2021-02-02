package com.shuang.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class KnowlegeAddClass {
    private int id;
    private String name;
    private String model_name;
    private String model_content;
    private MultipartFile model_file;
    private int pid;
    private int level;
    private int model_id;
    private String model_url;

}
