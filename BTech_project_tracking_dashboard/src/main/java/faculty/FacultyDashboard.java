package faculty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/faculty/dashboard")
public class FacultyDashboard extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114114856732018741L;
	private static String faculty_cookie = "a55535438557826c9097027828769fb888ee18ee708becedfec111ec4f31e24c";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		boolean isFaculty = false;
		Cookie ck[] = req.getCookies();  
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(faculty_cookie)) {
				isFaculty = true;
			}
		}
		if(isFaculty) {
			req.getRequestDispatcher("/WEB-INF/faculty/facultydashboard.jsp").forward(req, res);
		}
		else {
			req.setAttribute("error","Unauthorized access");
			req.getRequestDispatcher("../admin/login").forward(req, res);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("In post of faculty dash");
		out.close();
	}
	
}
