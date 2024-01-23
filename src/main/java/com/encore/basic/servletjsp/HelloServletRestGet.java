package com.encore.basic.servletjsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-get")
public class HelloServletRestGet extends HttpServlet {
    //json/objecctmapper
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //기본 패턴 : req를 받아, res를 return 해주는 형식

        ObjectMapper mapper = new ObjectMapper();

        //객체 조립
        Hello hello = new Hello();
        hello.setEmail("naver");
        hello.setName("test");
        hello.setPassword("123");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String string = mapper.writeValueAsString(hello);
        PrintWriter out = resp.getWriter();
        out.write(string);



        //버퍼를 통해 조립이 이루어지므로, 버퍼를 비우는 과정.
        out.flush();

        out.close();
    }

}
