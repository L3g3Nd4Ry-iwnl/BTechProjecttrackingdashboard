package faculty;

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
import javax.servlet.annotation.WebServlet;

import misc.Hasher;
import misc.DBConnectivity;

@WebServlet("/faculty/login")
public class FacultyLogin extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6790996307095414963L;
	private static String faculty_cookie = "a55535438557826c9097027828769fb888ee18ee708becedfec111ec4f31e24c";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/faculty/facultylogin.jsp").forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String usermail = req.getParameter("email");
		String userpass = req.getParameter("password");
		String password_from_db = null;
		String facultyid = null;
		try {
			Connection con = DBConnectivity.initializeDatabase();
			PreparedStatement st = con.prepareStatement("SELECT password, facultyid FROM faculty WHERE email=? LIMIT 1");
			st.setString(1,usermail);
			ResultSet rs = st.executeQuery();
			
			boolean account_valid = false;
			while (rs.next()) {
				account_valid = true;
		        password_from_db = rs.getString("password");
		        facultyid = rs.getString("facultyid");
		    }
			if (account_valid) {
				if(Hasher.verifyPassword(userpass, password_from_db)) {
					res.addCookie(new Cookie("user_type", faculty_cookie));
					res.addCookie(new Cookie("user_email",usermail));
					res.addCookie(new Cookie("user_id",facultyid));
					res.sendRedirect("../faculty/dashboard");
				}
				else {
					req.setAttribute("error","Wrong username and/or password");
					req.getRequestDispatcher("/WEB-INF/faculty/facultylogin.jsp").forward(req, res);
				}
			}
			else {
				req.setAttribute("error","User account doesn't exists! Contact the administrator");
				req.getRequestDispatcher("/WEB-INF/faculty/facultylogin.jsp").forward(req, res);
			}
			
			
			rs.close();
			st.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
