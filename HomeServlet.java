package hivescheduler.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;


    public HomeServlet(){

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        // We are now forwarding the user to the JSP page we created to handle logging in (WEB-INF/views/homeView.jsp)
        // We could have created a static HTML page here, but it is better to have a dynamic JSP page.
        // We forward them to the page and not just let them get to the page b/c users cannot access JSP pages we store in WEB-INF

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        this.doGet(request, response);
    }
}
