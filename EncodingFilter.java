package hivescheduler.filters;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "encodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {


    @Override
    public void init(FilterConfig fConfig) throws ServletException{

        System.out.println("Encoding Filter Initialization");
    }

    public void EncodingFilter(){

    }


    public void destructor(){

        System.out.println("Destroying Encoding Filter");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

       request.setCharacterEncoding("UTF-8");

       chain.doFilter(request, response);
    }
}
