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

@WebServlet("/admin/setup/project")

public class SetupProject extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4890131449469626378L;
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
			req.getRequestDispatcher("/WEB-INF/admin/setupproject.jsp").forward(req, res);
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
			String groupNo = req.getParameter("groupnumber");
			String project_title = req.getParameter("projectname");
			String facultyID = req.getParameter("mentorid");
			String panelID = req.getParameter("panelnumber");
			String student1ID = req.getParameter("studentrollnumber1");
			String student2ID = req.getParameter("studentrollnumber2");
			String student3ID = req.getParameter("studentrollnumber3");
			String student4ID = req.getParameter("studentrollnumber4");
			
			int status = -1;
			Connection con;
			try {
				con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("INSERT INTO project (groupNo, project_title, facultyID, panelID, student1ID, student2ID, student3ID, student4ID) VALUES (?,?,?,?,?,?,?,?);");
				st.setString(1,groupNo);
				st.setString(2,project_title);
				st.setString(3,facultyID);
				st.setString(4,panelID);
				st.setString(5,student1ID);
				st.setString(6,student2ID);
				st.setString(7,student3ID);
				st.setString(8,student4ID);
				
				status = st.executeUpdate();
		
				st.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			if(status>0) {
				getServletContext().setAttribute("message", "Project has been added!");
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
