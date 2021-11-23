<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
%>  
<%@ page import="javax.servlet.http.Cookie" %>
<%
    	String student_cookie = "6b1973c6b62161c16877794881fa31d928bbb3735d76cc170809657cde58512c";
    	String faculty_cookie = "a55535438557826c9097027828769fb888ee18ee708becedfec111ec4f31e24c";
    	String admin_cookie = "f07bf50b0455c2346f8883d7697a158b703338dddc3c7b4cd33e2c1b85df0711";
        %>
        <%
        String usertype = "";
		Cookie ck[] = request.getCookies();
		String fid = "";
		for(int i=0;i<ck.length;i++){   
			if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(faculty_cookie)) {
				usertype = "faculty";
			}
			else if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(admin_cookie)) {
				usertype = "admin";
			}
			else if(ck[i].getName().equals("user_type") && ck[i].getValue().equals(student_cookie)) {
				usertype = "student";
			}
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@500&family=Oxygen:wght@300&display=swap"
        rel="stylesheet">
    <title>Projects</title>

</head>

<body class="prj-body">
    <header class="prj-header">
        <h1>B.TECH PROJECT TRACKER</h1>
        <div class="logout-redirect">
            <form action="../../aboutus" method="GET">
                <button>
                    About
                </button>
            </form>
            <% String logout = "../../"+usertype+"/logout"; %>
            <form action=<% out.print(logout) ;%> method="POST">
                <button>
                    Log-out
                </button>
            </form>
        </div>
    </header>

    <main class="prj-main">
        <h1>Projects</h1>
        
        <% String temp = (String)request.getAttribute("projectList"); %>
		<% String[] projectList = temp.split(",")  ; %>
		<% if(projectList.length > 0){
			int counter = 0;
		%>
			<table>
		    	<th>S.NO</th>
		        <th>Project ID</th>
		        <th>Group ID</th>
		        <th>Project Name</th>
		        <th>View</th>		            
		<%
			while(counter < projectList.length){
		%>
				<tr>
	                <td><% out.print(projectList[counter]) ;%></td>
	                <td><% out.print(projectList[counter+1]) ;%></td>
	                <td><% out.print(projectList[counter+2]) ; %></td>
	                <td><% out.print(projectList[counter+3]) ;%></td>
	                <%String href = "../../"+usertype+"/view/project?id=" + projectList[counter]; %>              
					<td><a href=<% out.print(href) ;%>>View Project</a></td>
	                <%counter += 4; %>
	            </tr>
	        
	    <%}
		%>
		</table>
		<%
	    }else{ 
	    	out.print("No data available");
	    }
	    %>
	    
    </main>
</body>

</html>