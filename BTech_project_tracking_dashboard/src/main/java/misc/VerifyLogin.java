package misc;

import java.io.IOException;
import java.io.PrintWriter;

import misc.Hasher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/verify")
public class VerifyLogin {
	
	private static String hash = "37f326197305e05e775464ea05a7c875aa35747b66c33c0f7218a5734898eb0f";
	private static String email = "admin@amrita.edu";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String usermail = req.getParameter("email");
		String userpass = req.getParameter("password");
		if (usermail.equals(email)) {
			if(Hasher.verifyPassword(userpass, hash)) {
				req.getRequestDispatcher("/admin/dashboard").forward(req,res);
			}
		}
	}
}
