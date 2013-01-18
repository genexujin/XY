package edu.hp.view.security;


import edu.hp.view.utils.utils.Constants;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SecurityFilter implements Filter {
    private FilterConfig _filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                                     ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();

        Object loginUser = session.getAttribute(Constants.SECURITY_FILTER_SESSION_KEY);

        if (loginUser == null) {
            ((HttpServletResponse)response).sendRedirect(Constants.WEB_ROOT_CONTEXT + "/login.jsp");
        } else {

            chain.doFilter(request, response);

        }
    }
}
