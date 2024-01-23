package com.encore.basic.servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

//Servlet API를 사용하여 직접 서블릿을 정의하는 방식
//메서드 단위가 아닌 class 단위로 라우팅을 한다.
@WebServlet("/hello-servlet-jsp-post")
public class HelloServletJspPost extends HttpServlet {

    //사용자한테 넘겨 받고

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(name+" "+email+" "+password);

        //resp.setContentType("text/plain");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        //@ResponseBody + String
        PrintWriter out = resp.getWriter();

        //out.println(name);
        out.println("<html>");
        out.println("<head><title>My Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Hello, Servlet!</h1>");
        out.println("<p>"+name+"</p>");
        out.println("<p>"+email+"</p>");
        out.println("<p>"+password+"</p>");
        out.println("</body>");
        out.println("</html>");

        //버퍼를 통해 조립이 이루어지므로, 버퍼를 비우는 과정.
        out.flush();

        out.close();
    }

}
