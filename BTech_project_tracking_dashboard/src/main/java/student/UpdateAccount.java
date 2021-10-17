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

import javax.servlet.annotation.WebServlet;

@WebServlet("/student/profile")
public class UpdateAccount extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8749358834910413589L;
	private static String student_cookie = "6b1973c6b62161c16877794881fa31d928bbb3735d76cc170809657cde58512c";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
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
				PreparedStatement st = con.prepareStatement("SELECT name, department, email, roll_number, mobile_number  FROM student WHERE roll_number=? LIMIT 1");
				st.setString(1,roll_no);
				ResultSet rs = st.executeQuery();
				boolean available = false;
				while (rs.next()) {
					available = true;
					req.setAttribute("name",rs.getString("name"));
					req.setAttribute("department",rs.getString("department"));
					req.setAttribute("email",rs.getString("email"));
					req.setAttribute("roll_no",rs.getString("roll_number"));
					req.setAttribute("mobile",rs.getString("mobile_number"));
			    }
				if(available) {
					
					req.getRequestDispatcher("/WEB-INF/student/updateprofile.jsp").forward(req, res);
				}
				else {
					req.setAttribute("message", "Some server side error! Please try again");
					req.getRequestDispatcher("/WEB-INF/student/studentdashboard.jsp").forward(req, res);
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
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
				String new_mobile = req.getParameter("mobile");
				Connection con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("SELECT mobile_number FROM student WHERE roll_number=? LIMIT 1");
				st.setString(1,roll_no);
				ResultSet rs = st.executeQuery();
				boolean available = false;
				String old_mobile=null;
				while (rs.next()) {
					available = true;
					old_mobile = rs.getString("mobile_number");
			    }
				st.close();
				if(available) {
					if(old_mobile.equals(new_mobile)) {
						getServletContext().setAttribute("message", "No change in phone number was detected!");
						req.getRequestDispatcher("../student/dashboard").forward(req, res);
					}
					else if(new_mobile.equals("")){
						getServletContext().setAttribute("message", "No change in phone number was detected!");
						req.getRequestDispatcher("../student/dashboard").forward(req, res);
					}
					else {
						PreparedStatement st1 = con.prepareStatement("UPDATE student SET mobile_number = ? WHERE (roll_number = ?);");
						st1.setString(1, new_mobile);
						st1.setString(2,roll_no);
						int status = st1.executeUpdate();
						if(status>0) {
							getServletContext().setAttribute("message","Phone number updated successfully!");
							res.sendRedirect("../student/dashboard");
						}
						else {
							getServletContext().setAttribute("message","There was some error on server side! Please try again!");
							res.sendRedirect("../student/dashboard");
						}				
					}
				}
				else {
					getServletContext().setAttribute("message", "Some server side error! Please try again");
					res.sendRedirect("../student/dashboard");
				}
				con.close();
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

