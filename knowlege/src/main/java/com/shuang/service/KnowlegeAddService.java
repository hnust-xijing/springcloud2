package com.shuang.service;


import com.shuang.dao.AddDao;
import com.shuang.entity.KnowlegeAddClass;
import com.shuang.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

@Service
public class KnowlegeAddService {

    @Autowired
    private AddDao addDao;

    @Resource
    private PlatformTransactionManager transactionManager;

    public void AddModelAndList(KnowlegeAddClass knowlegeAddClass){
        //新增的知识里面的模块没有附件（Model_file），不需要文件上传，url也为空。
        if(knowlegeAddClass.getModel_file()==null)
        {

            knowlegeAddClass.setModel_url(null);

            //事务，保证两个操作，要么同时成功，要么同时失败
            //1，开启操作
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);

            try{

                //2，执行操作
                int id=addDao.AddList(knowlegeAddClass);
                knowlegeAddClass.setId(id);
                addDao.AddModel(knowlegeAddClass);
                //3，提交事务
                transactionManager.commit(status);
                //4，返回结果
                //return  aa;

            }catch(Exception e){
                //5,回滚操作
                //有异常便回滚事务
                transactionManager.rollback(status);
                e.printStackTrace();
            }

        }

        else {


            //事务，保证两个操作，要么同时成功，要么同时失败
            //1，开启操作
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);

            try{
                String model_url=null;
                model_url=UploadUtil.uploadFile(knowlegeAddClass.getModel_file());
                knowlegeAddClass.setModel_url(model_url);
                //2，执行操作
                int id=addDao.AddList(knowlegeAddClass);
                knowlegeAddClass.setId(id);
                addDao.AddModel(knowlegeAddClass);
                //3，提交事务
                transactionManager.commit(status);
                //4，返回结果
                //return  aa;

            }catch(Exception e){
                //5,回滚操作
                //有异常便回滚事务
                transactionManager.rollback(status);
                e.printStackTrace();
            }


        }


        }





    }


