package com.encore.basic.servletjsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-post")
public class HelloServletRestPost extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;

        //데이터 body 값 json인데 객체로 변환되지 않았었는데
        //mapper로 readValue 변환
        //출력하고
        //ok 리턴
        while (null != (line = br.readLine())) {
            sb.append(line);
        }

        //body, new TypeReference<List<Post1>>(){}
        String body = sb.toString();
        Hello hello = mapper.readValue(body, Hello.class);

        System.out.println(hello.getEmail());
        System.out.println(hello.getName());
        System.out.println(hello.getPassword());

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("OK");

    }

}
