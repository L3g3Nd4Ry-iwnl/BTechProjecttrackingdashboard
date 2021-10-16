package misc;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/admin/logout","/faculty/logout","/student/logout"})

public class Logout extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8360415648756682270L;
	
	private static String admin_cookie = "f07bf50b0455c2346f8883d7697a158b703338dddc3c7b4cd33e2c1b85df0711";
	private static String faculty_cookie = "a55535438557826c9097027828769fb888ee18ee708becedfec111ec4f31e24c";
	private static String student_cookie = "6b1973c6b62161c16877794881fa31d928bbb3735d76cc170809657cde58512c";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		 Cookie[] cookies = req.getCookies();
		 String user_type = "";
		    if (cookies != null)
		        for (Cookie cookie : cookies) {
		        	if(cookie.getName().equals("user_type")) {
		        		if(cookie.getValue().equals(admin_cookie)) {
			        		user_type = "admin";
			        	}
			        	else if (cookie.getValue().equals(faculty_cookie)) {
			        		user_type = "faculty";
			        	}
			        	else if(cookie.getValue().equals(student_cookie)) {
			        		user_type = "student";
			        	}
		        		cookie.setValue("");
		        		cookie.setMaxAge(0);
		        		res.addCookie(cookie);
		        	}
		        	if(cookie.getName().equals("user_mail")) {
		        		cookie.setValue("");
		        		cookie.setMaxAge(0);
		        		res.addCookie(cookie);
		        	}
		        }
		    if(!user_type.equals("")) {
		    	req.setAttribute("logout_msg", "You have been logged out successfully!");
		    	req.getRequestDispatcher("/WEB-INF/"+user_type+"/"+user_type+"login.jsp").forward(req, res);
		    }
		    else {
		    	req.setAttribute("error", "unauthorized access!");
		    	req.getRequestDispatcher("/WEB-INF/admin/adminlogin.jsp").forward(req, res);
		    }
		    
	}
}
