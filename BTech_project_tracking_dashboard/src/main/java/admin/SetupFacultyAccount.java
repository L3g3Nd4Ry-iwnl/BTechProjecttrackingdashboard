package admin;

import java.io.IOException;

import javax.servlet.ServletException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misc.DBConnectivity;
import misc.Hasher;

import javax.servlet.http.Cookie;

@WebServlet("/admin/setup/faculty")
public class SetupFacultyAccount extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 101379795688571747L;
	private static String admin_cookie = "f07bf50b0455c2346f8883d7697a158b703338dddc3c7b4cd33e2c1b85df0711";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isAdmin = false;
		Cookie ck[] = req.getCookies();  
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(admin_cookie)) {
				isAdmin = true;
			}
		}
		if(isAdmin) {
			req.getRequestDispatcher("/WEB-INF/admin/setupfacultyaccount.jsp").forward(req, res);
		}
		else {
			req.setAttribute("error","Unauthorized access");
			req.getRequestDispatcher("/WEB-INF/admin/adminlogin.jsp").forward(req, res);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isAdmin = false;
		Cookie ck[] = req.getCookies();  
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(admin_cookie)) {
				isAdmin = true;
			}
		}
		if(isAdmin) {
			String facultyID = req.getParameter("fid");
			String name = req.getParameter("name");
			String department = req.getParameter("department");
			String email = req.getParameter("email");
			String password_hash = Hasher.createHash(req.getParameter("password"));
			String mobilenum = req.getParameter("mobile");
			int status = -1;
			Connection con;
			try {
				con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("INSERT INTO faculty(facultyid, name, department, email, password, mobile_number) VALUES (?, ?, ?, ?, ?, ?);");
				st.setString(1,facultyID);
				st.setString(2,name);
				st.setString(3,department);
				st.setString(4,email);
				st.setString(5,password_hash);
				st.setString(6,mobilenum);
				status = st.executeUpdate();
				con.close();
				st.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status>0) {
				getServletContext().setAttribute("message", "Faculty account has been added!");
				res.sendRedirect("../../admin/dashboard");
			}
			else {
				getServletContext().setAttribute("message", "Some SQL error happened, please try again!");
				res.sendRedirect("../../admin/dashboard");
			}
		}
		else {
			req.setAttribute("error","Unauthorized access");
			req.getRequestDispatcher("/WEB-INF/admin/adminlogin.jsp").forward(req, res);
		}
	}
}
