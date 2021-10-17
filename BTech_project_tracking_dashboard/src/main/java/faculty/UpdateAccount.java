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

import misc.DBConnectivity;

import javax.servlet.annotation.WebServlet;

@WebServlet("/faculty/profile")
public class UpdateAccount extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8749358834910413589L;
	private static String faculty_cookie = "a55535438557826c9097027828769fb888ee18ee708becedfec111ec4f31e24c";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isFaculty = false;
		Cookie ck[] = req.getCookies();
		String fid = "";
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(faculty_cookie)) {
				isFaculty = true;
			}
			if(ck[i].getName().equals("user_id")) {
				fid = ck[i].getValue();
			}
		}
		if(isFaculty) {
			try {
				Connection con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("SELECT name, department, email, facultyid, mobile_number  FROM faculty WHERE facultyid=? LIMIT 1");
				st.setString(1,fid);
				ResultSet rs = st.executeQuery();
				boolean available = false;
				while (rs.next()) {
					available = true;
					req.setAttribute("name",rs.getString("name"));
					req.setAttribute("department",rs.getString("department"));
					req.setAttribute("email",rs.getString("email"));
					req.setAttribute("fid",rs.getString("facultyid"));
					req.setAttribute("mobile",rs.getString("mobile_number"));
			    }
				if(available) {
					
					req.getRequestDispatcher("/WEB-INF/faculty/updateprofile.jsp").forward(req, res);
				}
				else {
					req.setAttribute("message", "Some server side error! Please try again");
					req.getRequestDispatcher("/WEB-INF/faculty/facultydashboard.jsp").forward(req, res);
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			req.setAttribute("error","Unauthorized access");
			req.getRequestDispatcher("../faculty/login").forward(req, res);
		}
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isFaculty = false;
		Cookie ck[] = req.getCookies();
		String fid = "";
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(faculty_cookie)) {
				isFaculty = true;
			}
			if(ck[i].getName().equals("user_id")) {
				fid = ck[i].getValue();
			}
		}
		if(isFaculty) {
			try {
				String new_mobile = req.getParameter("mobile");
				Connection con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("SELECT mobile_number FROM faculty WHERE facultyid=? LIMIT 1");
				st.setString(1,fid);
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
						req.getRequestDispatcher("../faculty/dashboard").forward(req, res);
					}
					else if(new_mobile.equals("")){
						getServletContext().setAttribute("message", "No change in phone number was detected!");
						req.getRequestDispatcher("../faculty/dashboard").forward(req, res);
					}
					else {
						PreparedStatement st1 = con.prepareStatement("UPDATE faculty SET mobile_number = ? WHERE (facultyid = ?);");
						st1.setString(1, new_mobile);
						st1.setString(2,fid);
						int status = st1.executeUpdate();
						if(status>0) {
							getServletContext().setAttribute("message","Phone number updated successfully!");
							res.sendRedirect("../faculty/dashboard");
						}
						else {
							getServletContext().setAttribute("message","There was some error on server side! Please try again!");
							res.sendRedirect("../faculty/dashboard");
						}				
					}
				}
				else {
					getServletContext().setAttribute("message", "Some server side error! Please try again");
					res.sendRedirect("../faculty/dashboard");
				}
				con.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getServletContext().setAttribute("error","Unauthorized access");
			res.sendRedirect("../faculty/login");
		}
	}
}
