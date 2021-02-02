
package com.shuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docketInformationInput(Environment environment){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("你好，世界!")
                .apiInfo(apiInfo())
                .enable(true)//enable是否启动Swagger,如果为false,则Swagger不能在浏览器中访问。
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shuang.controller"))
                .build();
    }





    //配置swagger信息apiInfo
    private ApiInfo apiInfo() {
        Contact contack=new Contact("js","https://www.shishuangzhi.xyz","2894247242@qq.com");

        return new ApiInfo(
                "爽宝的Swagger API文档",
                "爽宝，爽爽爽",
                "1.0",
                "https://www.shishuangzhi.xyz",
                contack,
                "Apache 2.0",
                "http://www.apache.org/license/LICENSE-2.0",
                new ArrayList()
        );

    }


}

//package com.shuang.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//
//@Configuration
//@EnableSwagger2 //开启Swagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket docketInformationInput(Environment environment){
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("informationInput")
//                .apiInfo(apiInfo())
//                .enable(true)//enable是否启动Swagger,如果为false,则Swagger不能在浏览器中访问。
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.shuang.controller.informationInput"))
//                .build();
//    }
//
//    //配置swagger信息apiInfo
//    private ApiInfo apiInfo() {
//        Contact contack=new Contact("js","https://www.shishuangzhi.xyz","2894247242@qq.com");
//
//        return new ApiInfo(
//                "爽宝的Swagger API文档",
//                "爽宝，爽爽爽",
//                "1.0",
//                "https://www.shishuangzhi.xyz",
//                contack,
//                "Apache 2.0",
//                "http://www.apache.org/license/LICENSE-2.0",
//                new ArrayList()
//        );
//
//    }
//
//
//}
