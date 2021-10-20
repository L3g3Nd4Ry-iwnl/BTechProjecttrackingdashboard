package student;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/dashboard")
public class StudentDashboard extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4999249212827041615L;
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
			req.getRequestDispatcher("/WEB-INF/student/studentdashboard.jsp").forward(req, res);
		}
		else {
			req.setAttribute("error","Unauthorized access");
			req.getRequestDispatcher("../student/login").forward(req, res);
		}
	}

}
