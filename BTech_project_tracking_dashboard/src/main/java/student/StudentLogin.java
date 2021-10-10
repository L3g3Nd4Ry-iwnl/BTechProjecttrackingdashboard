package student;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentLogin extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3990401223778787859L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<!DOCTYPE html><html lang=\"en\"><head> <meta charset=\"utf-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <link rel=\"stylesheet\" href=\"../css/styles.css\"> <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\"> <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin> <link href=\"https://fonts.googleapis.com/css2?family=Orbitron:wght@500&family=Oxygen:wght@300&display=swap\" rel=\"stylesheet\"> <title>Student Login</title></head><body class=\"login-body\"> <header class=\"login-header\"> <h1>B.TECH PROJECT TRACKER</h1> <div class=\"redirect\"> <form action=\"../\" method=\"GET\"> <button> Home </button> </form> <form action=\"../aboutus\" method=\"GET\"> <button> About </button> </form> <form action=\"../faculty/login\" method=\"GET\"> <button> Faculty Login </button> </form> <form action=\"../admin/login\" method=\"GET\"> <button> Admin Login </button> </form> </div></header> <main class=\"login-form\"> <img src=\"../images/project.jpg\" height=\"auto\" width=\"auto\"> <div class=\"form-details\"> <h1>Welcome Back</h1> <form class=\"login-form-class\" action=\"../student/login\" method=\"POST\"> <div> <div class=\"form-container\"> <label for=\"email\"><strong>Email</strong></label> <input type=\"email\" placeholder=\"Enter your Email\" name=\"email\"> </div></div><div> <div class=\"form-container\"> <label for=\"password\"><strong>Password</strong></label> <input type=\"password\" placeholder=\"Enter your Password\" name=\"password\"> </div></div><button type=\"submit\" value=\"admin\">Submit</button> </form> </div></main></body></html>");
		out.close();
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
