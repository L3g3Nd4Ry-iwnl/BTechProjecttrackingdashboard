package misc;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

@WebServlet("/temp")
public class Temp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3482970439665864257L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			try {
				Connection con = DBConnectivity.initializeDatabase();
				PreparedStatement st = con.prepareStatement("SELECT * FROM project");
				ResultSet rs = st.executeQuery();
				@SuppressWarnings("unused")
				ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
				int rowCount = 0;
				ResultSet temp = rs;
			    while (temp.next()) {
			    	rowCount++;
			    }
			    System.out.println("The count is:"+rowCount);
			    
			    ResultSet rs1 = st.executeQuery();
			    String[][] projectList = new String[rowCount][4];
			    int counter = 0;
			    while(rs1.next()) {
			    	String[] tempList = new String [4];
			    	tempList[0] = Integer.toString(counter+1);
			    	tempList[1] = rs1.getString("projectID");
			    	tempList[2] = rs1.getString("groupNo");
			    	tempList[3] = rs1.getString("project_title");
			    	projectList[counter++] = tempList;
			    }
			    System.out.println(Arrays.deepToString(projectList));
				req.setAttribute("projectList", projectList);				
				req.getRequestDispatcher("/WEB-INF/student/viewprojects.jsp").forward(req, res);
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
	}
	

}
