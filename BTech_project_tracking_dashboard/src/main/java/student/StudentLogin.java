package student;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misc.DBConnectivity;
import misc.Hasher;

import javax.servlet.annotation.WebServlet;

@WebServlet("/student/login")
public class StudentLogin extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3990401223778787859L;
	private static String student_cookie = "6b1973c6b62161c16877794881fa31d928bbb3735d76cc170809657cde58512c";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		req.getRequestDispatcher("/WEB-INF/student/studentlogin.jsp").forward(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String usermail = req.getParameter("email");
		String userpass = req.getParameter("password");
		String password_from_db = null;
		try {
			Connection con = DBConnectivity.initializeDatabase();
			PreparedStatement st = con.prepareStatement("SELECT password FROM student WHERE email=? LIMIT 1");
			st.setString(1,usermail);
			ResultSet rs = st.executeQuery();
			
			res.setContentType("text/html");
			boolean account_valid = false;
			while (rs.next()) {
				account_valid = true;
		        password_from_db = rs.getString("password");
		    }
			if (account_valid) {
				if(Hasher.verifyPassword(userpass, password_from_db)) {
					res.addCookie(new Cookie("user_type", student_cookie));
					res.addCookie(new Cookie("user_email",usermail));
					res.sendRedirect("../student/dashboard");
				}
				else {
					req.setAttribute("error","Wrong username and/or password");
					req.getRequestDispatcher("/WEB-INF/student/studentlogin.jsp").forward(req, res);
				}
			}
			else {
				req.setAttribute("error","User account doesn't exists! Contact the administrator");
				req.getRequestDispatcher("/WEB-INF/student/studentlogin.jsp").forward(req, res);
			}
			
			
			rs.close();
			st.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
