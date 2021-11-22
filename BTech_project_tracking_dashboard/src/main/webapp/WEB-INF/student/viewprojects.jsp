<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
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
            <form action="../../student/logout" method="GET">
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
	                <%String href = "../../student/view/project?id=" + projectList[counter]; %>              
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