package edu.hp.view.security;


import edu.hp.view.utils.Constants;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;


public class SignInServlet extends HttpServlet {
    
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String LOGIN_PAGE_NAME = "/login.jsp";
    private static final String ACTION_LOGIN = "signin";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String userName = request.getParameter("userid");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);

        if (userName != null && password != null && action != null && action.equals(ACTION_LOGIN)) {
            String sql =
                "select employees.user_name," + "employees.first_name, " + "employees.last_name, " + "employees.gender," +
                "roles.role_name, employees.id " + " from employees,role_users,roles " + " where " +
                " employees.id = role_users.user_id " + " and roles.role_id = role_users.role_id " +
                " and employees.user_name = ? " + " and employees.password = ? ";
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                // get JNDI JDBC connection
                InitialContext ctxt = new InitialContext();
                DataSource ds = (DataSource)ctxt.lookup("jdbc/oaDS");
                conn = ds.getConnection();
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, userName);
                stmt.setString(2, password);
                rs = stmt.executeQuery();

                boolean first = true;
                LoginUser user = null;

                while (rs.next()) {
                    if (first) {
                        user = new LoginUser();
                        user.setUserName(rs.getString(1));
                        user.setDisplayName(rs.getString(3) + rs.getString(2));                        
                        user.setUserRoles(new ArrayList<String>());                       
                        user.setIsUserInRole( new HashMap<String,Boolean>());  
                        user.setUserId(rs.getString(6));
                        first = false;
                    }
                    
                    user.getIsUserInRole().put(rs.getString(5), true);
                    user.getUserRoles().add(rs.getString(5));

                }

                if (user != null) {
                                       
                    session.setAttribute(Constants.SECURITY_FILTER_SESSION_KEY, user);
                    //LoginUser usernew = (LoginUser)session.getAttribute(Constants.SECURITY_FILTER_SESSION_KEY);
                    //request.getRequestDispatcher(Constants.WEB_ROOT_CONTEXT + "/faces/Home").forward(request, response);
                    response.sendRedirect(Constants.WEB_ROOT_CONTEXT + "/faces/Home");
                                        
                } else {
                    response.sendRedirect(Constants.WEB_ROOT_CONTEXT + "/login_error.jsp");
                }

            } catch (Exception sqle) {
                // TODO: Add catch code
                sqle.printStackTrace();
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                    if (rs != null)
                        rs.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException sqle) {
                    // TODO: Add catch code
                    sqle.printStackTrace();
                }
            }

        } else {
            session.invalidate();
            response.sendRedirect(Constants.WEB_ROOT_CONTEXT + LOGIN_PAGE_NAME);
        }

    }
}
