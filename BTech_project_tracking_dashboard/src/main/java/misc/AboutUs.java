package misc;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AboutUs extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4183966790022848169L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		req.getRequestDispatcher("/WEB-INF/aboutus.jsp").forward(req, res);
	}
}
