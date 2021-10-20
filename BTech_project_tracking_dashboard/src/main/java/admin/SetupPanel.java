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

import javax.servlet.http.Cookie;

@WebServlet("/admin/setup/panel")

public class SetupPanel extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5137308608826572778L;
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
			req.getRequestDispatcher("/WEB-INF/admin/setuppanel.jsp").forward(req, res);
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
			String panelNo = req.getParameter("panelno");
			String facultyid1 = req.getParameter("faculty1");
			String facultyid2 = req.getParameter("faculty2");
			String facultyid3 = req.getParameter("faculty3");
			int status = -1;
			Connection con;
			try {
				con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("INSERT INTO panel (panelID, faculty1ID, faculty2ID, faculty3ID) VALUES (?,?,?,?);");
				st.setString(1,panelNo);
				st.setString(2,facultyid1);
				st.setString(3,facultyid2);
				st.setString(4,facultyid3);				
				status = st.executeUpdate();
				st.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			if(status>0) {
				getServletContext().setAttribute("message", "Panel has been added!");
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
