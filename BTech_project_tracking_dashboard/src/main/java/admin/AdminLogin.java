package admin;

import java.io.IOException;
import misc.Hasher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

@WebServlet("/admin/login")
public class AdminLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1700635895394014993L;
	
	private static String hash = "37f326197305e05e775464ea05a7c875aa35747b66c33c0f7218a5734898eb0f";
	private static String email = "admin@amrita.edu";
	private static String admin_cookie = "f07bf50b0455c2346f8883d7697a158b703338dddc3c7b4cd33e2c1b85df0711";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/admin/adminlogin.jsp").forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String usermail = req.getParameter("email");
		String userpass = req.getParameter("password");
		if (usermail.equals(email)) {
			if(Hasher.verifyPassword(userpass, hash)) {
				res.addCookie(new Cookie("user_type", admin_cookie));
				res.addCookie(new Cookie("user_email","admin"));
				res.sendRedirect("../admin/dashboard");	
			}
			else {
				req.setAttribute("error","Wrong username and/or password");
				req.getRequestDispatcher("/WEB-INF/admin/adminlogin.jsp").forward(req, res);
			}
		}
		else {
			req.setAttribute("error","Wrong username and/or password");
			req.getRequestDispatcher("/WEB-INF/admin/adminlogin.jsp").forward(req, res);
		}
	}
}
