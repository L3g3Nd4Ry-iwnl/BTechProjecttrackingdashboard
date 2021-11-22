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

@WebServlet({"/student/view/projects","/faculty/view/projects","/admin/view/projects"})
public class ViewProjects extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3482970439665264257L;
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
				PreparedStatement st = con.prepareStatement("SELECT * FROM project");		    
			    ResultSet rs1 = st.executeQuery();
			    String projectList = "";
			    int counter = 1;
			    while(rs1.next()) {
			    	projectList+= Integer.toString(counter++) + ",";
			    	projectList+= rs1.getString("projectID")+ ",";
			    	projectList+= rs1.getString("groupNo")+ ",";
			    	projectList+= rs1.getString("project_title")+ ",";
			    }
			    projectList = projectList.substring(0, projectList.length()-1);
				req.setAttribute("projectList", projectList);				
				req.getRequestDispatcher("/WEB-INF/student/viewprojects.jsp").forward(req, res);
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
