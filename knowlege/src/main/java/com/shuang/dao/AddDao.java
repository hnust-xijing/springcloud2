package com.shuang.dao;


import com.shuang.entity.AddListClass;
import com.shuang.entity.AddModelClass;
import com.shuang.entity.KnowlegeAddClass;
import org.springframework.stereotype.Repository;

@Repository
public interface AddDao {

    public int  AddList(KnowlegeAddClass knowlegeAddClass);
    public void AddModel(KnowlegeAddClass knowlegeAddClass);



}
