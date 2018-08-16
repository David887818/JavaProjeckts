package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = {"/homeAdmin", "/homeUser","/addAdsServlet"})
public class AuthorizationFilter implements Filter {

    List<String> exceptUrl = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        exceptUrl.add(".css");
        exceptUrl.add(".js");
        exceptUrl.add(".png");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (!exceptUrl.contains(httpServletRequest.getRequestURI())) {
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                ((HttpServletResponse) servletResponse).sendRedirect("/");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
