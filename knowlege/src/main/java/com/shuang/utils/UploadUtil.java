package com.shuang.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;



public class UploadUtil {



    public static String uploadFile(MultipartFile file) {//file要和前端的表单文件name一致
       String url;
        try {
//            String oldFileName = file.getOriginalFilename();//获取文件原始名称
            //创建文件在服务器端的存放路径
            String dir = "C:\\Files\\";
//            String dir=request.getServletContext().getRealPath("C:\\Files\\");
            //文件保存路径
            File fileDir = new File(dir);//生成绝对路径
            if (!fileDir.exists())
                fileDir.mkdirs();
            //生成文件在服务器端存放的名字
//            String fileSuffix="number";
            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//截取文件扩展名
            String fileName = UUID.randomUUID().toString() + fileSuffix;//生成全名
            File files = new File(fileDir + "/" + fileName);    //封装
            //上传
            file.transferTo(files);
            //文件路径
            url=dir+fileName;
            // url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + fileName;
            //把路径保存到数据库
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
       // return "上传成功！";
        return url;
    }

//实现多个文件上传
//多个个文件上传，用数组
    public static String[] uploadFiles(MultipartFile[] file) {//file要和前端的表单文件name一致,numb表示
        String[] url =new String[file.length];
        try {
            //创建文件在服务器端的存放路径
//            String dir = request.getServletContext().getRealPath("C:\\Files\\");
            String dir = "C:\\Files\\";

            File fileDir = new File(dir);//生成绝对路径
            if (!fileDir.exists())
                fileDir.mkdirs();
            //生成文件在服务器端存放的名字
            for (int i = 0; i < file.length; i++) {
                String fileSuffix = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));//截取文件扩展名
                String fileName = UUID.randomUUID().toString() + fileSuffix;//生成全名
                //封装
                File files = new File(fileDir + "/" + fileName);
                //上传
                file[i].transferTo(files);
                url[i]=dir+fileName;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return url;
    }


    //实现文件下载
    public static String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename="1a2353be-ec33-407d-b749-52a4d15abe9c.docx";
        String filePath = "C:/Files" ;//下载地址
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment; fileName="+  filename +";filename*=utf-8''"+URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
