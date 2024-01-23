package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//HTTP 통신을 편하게 할 수 있다.
//@RestController --> restfull 하게만 쓰겠다는 뜻, 모든 요청에 ResponseBody를 붙이고 싶다면 사용
@Controller
@RequestMapping("hello")
//클래스 차원에서 url을 사용하고 싶다면 @RequestMapping을 선언해서 사용할 수 있다.
public class HelloController {
    //@ResponseBody가 없고 String으로 리턴하면 tymeleaf 템플릿이 없어서 에러가 난다.
    //data만을 return할 때는 @ResponseBody를 붙여야 한다.
    //@GetMapping("string")
    @RequestMapping(value = "string", method = RequestMethod.GET)
    @ResponseBody
    public String ping () {
        return "OK";
    }

    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }

    @GetMapping("screen-model")
    public String helloScreenModel(Model model) {
        //화면에 data를 넘기고 싶을때
        //model에 key:value 형식으로 전달
        //템플릿엔진기술을 사용해서.
        model.addAttribute("myData","홍길동");
        return "screen";
    }

    @GetMapping("screen-model-param")
    //parameter ?키=밸류
    public String helloScreenModelParam(@RequestParam(value="name")String inputName, Model model) {
        //화면에 data를 넘기고 싶을때
        //model에 key:value 형식으로 전달
        //템플릿엔진기술을 사용해서.s
        model.addAttribute("myData",inputName);
        return "screen";
    }

    //localhost:8080/member/1
    //pathvariable
    //pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어, restAPi에 적합하다
    @GetMapping("screen-model-path/{id}")
    //parameter ?키=밸류
    public String helloScreenModelPath(@PathVariable(value="id")int id, Model model) {
        //화면에 data를 넘기고 싶을때
        //model에 key:value 형식으로 전달
        //템플릿엔진기술을 사용해서.
        model.addAttribute("myData",id);
        Hello h = new Hello();

        return "screen";
    }

    @GetMapping("json")
    @ResponseBody
    public Hello helloJson(HttpServletResponse response) throws IOException {
//        Hello hello = new Hello();
//        hello.setEmail("naver");
//        hello.setName("test");
//        hello.setPassword("123");
//        response.setContentType("text/plain");
//        PrintWriter out = response.getWriter();
//        out.print(hello.toString());
//        out.close();

        Hello hello = Hello.builder()
                .email("qwe")
                .name("12")
                .password("123")
                .build();

        return hello;
    }

    //get 요청으로 화면을 주고
    @GetMapping("form-screen")
    public String formScreen() {
        return "form_screen";
    }
    //form tag로 x-www 데이터 처리
    //
    @PostMapping("form-post-handle")
    public String formPostHandle(@RequestParam(value = "email") String email,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "password") String password,
                                 Model model)
    {

        System.out.println(email);
        System.out.println(name);
        System.out.println(password);
        model.addAttribute("email",email);
        model.addAttribute("name",name);
        model.addAttribute("password",password);
            return "screen";
    }
    //json 처리

    @GetMapping("json-screen")
    public String jsonScreen() {
        return "hello-json-screen";
    }
    //requestbody는 json으로 post요청이 들어왔을때 body에서 data를 꺼내기 위해 사용
    @PostMapping("/json-post-handle")
    @ResponseBody
    public String jsonScreenData(@RequestBody Hello hello) {

        System.out.println(hello.getEmail());
        return "/hello/screen";
    }

    @PostMapping("httpservlet")
    @ResponseBody
    public String httpServletTest(HttpServletRequest req) {

        //header 정보 추출
        System.out.println(req.getContentType());
        System.out.println(req.getMethod());
        //session
        System.out.println("se "+req.getSession());
        System.out.println(req.getHeader("Accept"));

        //body 정보 추출
        //req.getReader() 를 통해 BufferedReader를 받아 직접  파싱
        System.out.println(req.getParameter("test2"));
        System.out.println(req.getParameter("test1"));


        return "OK";
    }

    @GetMapping("hello-servlet-jsp-get")
    public String jspGet(Model model) {

        model.addAttribute("myData", "jsp test data");
        return "hello-jsp";
    }



}
