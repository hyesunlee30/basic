package com.encore.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //마지막 슬래쉬까지 처야 나온다
    //http://localhost:8080/swagger-ui/
    //Docket은 : Swagger 구성의 핵심 기능 클래스
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() // 어떤 컨트롤러 또는 어떤 api를 Swagger문서에 포함시킬지 선택
                .apis(RequestHandlerSelectors.any())  // 모든 RequestHandler를 대상으로 선택한다는 설정
                //** 자손 모두, *은 바로 하위만
                //memberRest 컨트롤러 경로를 의미
                //.paths(PathSelectors.any()) : 모든 path의 컨트롤러에 swagger 적용
                .paths(PathSelectors.ant("/memberRest/**"))
                .build();
    }

    //swagger의 authorize 자물쇠 버튼 활성화를 위해서는 jwt session 등의 별더의 설정 필요

}
