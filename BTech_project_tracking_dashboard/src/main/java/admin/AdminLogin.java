package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1700635895394014993L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		req.getRequestDispatcher("/WEB-INF/admin/adminlogin.jsp").forward(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String usermail = req.getParameter("email");
		String userpass = req.getParameter("password");
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<h1> Username is:"+usermail+", Password is:"+userpass);
		out.close();
	}
}
