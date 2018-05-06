package guauguapp;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

import com.jaunt.JauntException;
import com.jaunt.UserAgent;

import org.jsoup.Connection.Response;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
      
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    response.getWriter().print("Hello App Engine!\r\n");
    try{
    	  UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
    	  userAgent.visit("http://oracle.com");                        //visit a url  
    	  System.out.println(userAgent.doc.innerHTML());               //print the document as HTML
    	}
    	catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
    	  System.err.println(e);
    	}

  }
}