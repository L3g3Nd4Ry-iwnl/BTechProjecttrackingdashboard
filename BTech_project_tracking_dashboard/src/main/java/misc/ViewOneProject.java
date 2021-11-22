package misc;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/student/view/project","/faculty/view/project","/admin/view/project"})
public class ViewOneProject extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4662917803626348287L;
	private static String student_cookie = "6b1973c6b62161c16877794881fa31d928bbb3735d76cc170809657cde58512c";
	private static String faculty_cookie = "a55535438557826c9097027828769fb888ee18ee708becedfec111ec4f31e24c";
	private static String admin_cookie = "f07bf50b0455c2346f8883d7697a158b703338dddc3c7b4cd33e2c1b85df0711";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isValid = false;
		Cookie ck[] = req.getCookies();  
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && (ck[i].getValue().equals(student_cookie) || ck[i].getValue().equals(faculty_cookie) || ck[i].getValue().equals(admin_cookie))) {
				isValid = true;
			}
		}
		if(isValid) {
			try {
				Connection con = DBConnectivity.initializeDatabase();
				String projectID = req.getParameter("id");
				PreparedStatement st = con.prepareStatement("SELECT * FROM project WHERE projectID = ? LIMIT 1");
				st.setString(1, projectID);
				ResultSet rs = st.executeQuery();
				String projectDetails = "";
				while(rs.next()) {
					projectDetails += rs.getString("project_title") + ",";
					projectDetails += rs.getString("groupNo") + ",";
					projectDetails += rs.getString("student1ID") + ",";
					projectDetails += rs.getString("student2ID") + ",";
					projectDetails += rs.getString("student3ID") + ",";
					projectDetails += rs.getString("student4ID") + ",";
					projectDetails += rs.getString("facultyID") + ",";
					projectDetails += rs.getString("panelID") + ",";
				}
				projectDetails = projectDetails.substring(0, projectDetails.length()-1);
				req.setAttribute("projectDetails", projectDetails);
				st.close();
				rs.close();
				req.setAttribute("moduleDetails", "Documentation,HTML,CSS,Js");
				req.getRequestDispatcher("/WEB-INF/student/projectpage.jsp").forward(req, res);			
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			req.setAttribute("error","Unauthorized access");
			req.getRequestDispatcher("../student/login").forward(req, res);
		}
	}
}
