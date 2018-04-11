package hivescheduler.filters;


import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Connection;
import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Map;


// This is used to import the Filter interface to be implemented.
// It defines methods that allow us to filter requests to make sure they meet certain requirements.
import javax.servlet.Filter;

// This is necessary to be able to chain various filters together.
// Calling "chain.doFilter(request, response) transfer control to the next filter.
import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;

// This is used to get the registered servlets in the context (Tomcat sets context as webApp project name ("HiveScheduler" in this case)).
import javax.servlet.ServletRegistration;

import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


import hivescheduler.utils.MyUtils;
import hivescheduler.conn.ConnectionUtils;


@WebFilter(filterName= "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter{

    public JDBCFilter(){

    }


    @Override
    public void init(FilterConfig fConfig) throws ServletException{

        System.out.println("JDBC Filter Init");
    }


    @Override
    public void destroy(){

        System.out.println("JDBC Filter Destroy");
    }


    // Check that the target of the request is a servlet and as such, requires JDBC

    private boolean needsJDBC(HttpServletRequest request){

        String servletPath = request.getServletPath();

        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;


        if(pathInfo != null){

            urlPattern = servletPath + urlPattern;
        }


        Map <String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();


        Collection <? extends ServletRegistration> servlets = servletRegistrations.values();

        for (ServletRegistration servlet : servlets){

            Collection<String> maps = servlet.getMappings();

            if (maps.contains(urlPattern)){

                return true;
            }
        }

        return false;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if(this.needsJDBC(req)) {

            Connection conn = null;

            try {

                conn = ConnectionUtils.getConn();

                conn.setAutoCommit(false);

                MyUtils.storeConn(request, conn);

                chain.doFilter(request, response);

                conn.commit();
            } catch (Exception e) {

                e.printStackTrace();

                ConnectionUtils.rollbackConn(conn);

                throw new ServletException();
            }
        }

        else{

            chain.doFilter(request,response);
        }

    }
}
