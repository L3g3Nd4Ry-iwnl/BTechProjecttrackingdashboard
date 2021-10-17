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

@WebServlet("/student/changepass")
public class ChangePassword extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430435998554512412L;
	private static String student_cookie = "6b1973c6b62161c16877794881fa31d928bbb3735d76cc170809657cde58512c";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isStudent = false;
		Cookie ck[] = req.getCookies();
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(student_cookie)) {
				isStudent = true;
			}
		}
		if(isStudent) {
			req.getRequestDispatcher("/WEB-INF/student/changepassword.jsp").forward(req, res);
		}
		else {
			req.setAttribute("error","Unauthorized access");
			req.getRequestDispatcher("../student/login").forward(req, res);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isStudent = false;
		Cookie ck[] = req.getCookies();
		String roll_no = "";
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(student_cookie)) {
				isStudent = true;
			}
			if(ck[i].getName().equals("user_id")) {
				roll_no = ck[i].getValue();
			}
		}
		if(isStudent) {
			try {
				Connection con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("SELECT password  FROM student WHERE roll_number=? LIMIT 1");
				st.setString(1,roll_no);
				ResultSet rs = st.executeQuery();
				String password_from_db = "";
				String old_pass = Hasher.createHash(req.getParameter("password0"));
				String new_pass = Hasher.createHash(req.getParameter("password1"));
				String re_new_pass = Hasher.createHash(req.getParameter("password2"));
				boolean available = false;
				while (rs.next()) {
					available = true;
					password_from_db = rs.getString("password");
			    }
				if(available) {
					if(password_from_db.equals(old_pass)) {
						if(new_pass.equals(re_new_pass)) {
							PreparedStatement st1 = con.prepareStatement("UPDATE student SET password = ? WHERE (roll_number = ?);");
							st1.setString(1, new_pass);
							st1.setString(2, roll_no);
							int status = st1.executeUpdate();
							if(status>0) {
								getServletContext().setAttribute("message","Password updated successfully!");
								res.sendRedirect("../student/dashboard");
							}
							else {
								getServletContext().setAttribute("message","There was some error on server side! Please try again!");
								res.sendRedirect("../student/dashboard");
							}	
						}
						else {
							getServletContext().setAttribute("message", "New passwords didn't match! Please try again!");
							res.sendRedirect("../student/dashboard");
						}
					}
					else {
						getServletContext().setAttribute("message", "Your old password didn't match with our database! Please try again!");
						res.sendRedirect("../student/dashboard");
					}					
				}
				else {
					getServletContext().setAttribute("message", "Some server side error! Please try again");
					res.sendRedirect("../student/dashboard");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getServletContext().setAttribute("error","Unauthorized access");
			res.sendRedirect("../student/login");
		}
	}
}
