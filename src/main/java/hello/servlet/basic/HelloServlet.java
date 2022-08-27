package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //Servlet 을 호출하면 service 함수가 실행된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request); //org.apache.catalina.connector.RequestFacade@747e23d4
        System.out.println("response = " + response); //org.apache.catalina.connector.ResponseFacade@59799c84

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain"); //response content type header 정보에 들어감
        response.setCharacterEncoding("utf-8"); //response content type header 정보에 들어감
        response.getWriter().write("Hello " + username);

    }
}
