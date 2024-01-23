package com.encore.basic.servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Controller가 아닌 WebServlet을 통해 라우팅
//메서드 단위가 아닌 class 단위로 라우팅을 한다.
//경로 라우팅 해주는 것
//Servlet API를 사용하여 직접 서블릿을 정의하는 방식
@WebServlet("/hello-servlet-jsp-get")
public class HelloServletJspGet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //기본 패턴 : req를 받아, res를 return 해주는 형식

        System.out.println("start");

        //이걸 jsp 화면에 던질거다
        req.setAttribute("myData","jsp test data");
        req.getRequestDispatcher("WEB-INF/views/hello-jsp.jsp").forward(req, resp);
    }

    //service() 메서드는 서블릿에 들어오는 모든요청(get, post, put, delete 등)을 처리
    //다만 구체적으로 doGet, doPost 등의 메서드를 쓰는게 더 좋은 문법
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        //기본 패턴 : req를 받아, res를 return 해주는 형식
//
//        System.out.println("start");
//
//        //이걸 jsp 화면에 던질거다
//        req.setAttribute("myData","jsp test data");
//        req.getRequestDispatcher("WEB-INF/views/hello-jsp.jsp").forward(req, resp);
//    }

}
