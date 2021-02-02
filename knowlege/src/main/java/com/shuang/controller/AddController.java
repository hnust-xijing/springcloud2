package com.shuang.controller;


import com.shuang.entity.KnowlegeAddClass;
import com.shuang.service.KnowlegeAddService;
import com.shuang.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {

    @Autowired
    private KnowlegeAddService knowlegeAddService;


    @ApiOperation("知识添加")
    @PostMapping("/knowlege_add")
    public ResultUtil knowlege_add(@RequestBody KnowlegeAddClass knowlegeAddClass){

        try{
            knowlegeAddService.AddModelAndList(knowlegeAddClass);
        }catch(Exception e){
            e.printStackTrace();
            return ResultUtil.fail("知识添加失败");
        }return ResultUtil.succ("1","知识添加成功");
    }


}
