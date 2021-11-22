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
    <title>My Project</title>

</head>

<body class="prjpage-body">
    <header class="prjpage-header">
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

    <main class="prjpage-main">
    	<% String temp = (String)request.getAttribute("projectDetails"); %>
		<% String[] projectList = temp.split(",")  ; %>
		<% String[] moduleList = ((String)request.getAttribute("moduleDetails")).split(",") ;%>
        <h1><% out.print(projectList[0]) ;%></h1>
        <div class="prjpage-divider">
            <div class="prj-details">
                <h2 class="headings">Group No: </h2> <% out.print(projectList[1]) ;%>
                <h2 class="headings">Group Members: </h2> <% out.print(projectList[2]+ "<br/>" + projectList[3]+ "<br/>" + projectList[4]+ "<br/>" + projectList[5]);%>
                <h2 class="headings">Guide Faculty: </h2> <% out.print(projectList[6]) ;%>
                <h2 class="headings">Panel No: </h2> <% out.print(projectList[7]) ;%>
            </div>
			
            <div class="prj-modules">
            	<h2 class="hh">Modules:</h2>
            <%
            	int counter = 0;
				while(counter < moduleList.length){
			%>
                <div class="modulecontent">
                    <h3 class="modulenumber">Module <% out.print(counter+1) ;%>:</h3>
                    <h3 class="modulename"><% out.print(moduleList[counter++]) ;%></h3>
                </div>
            <% } %>
            <!-- 
            	<form action="../html/addmodule.html" method="GET">
                    <button class="addmodulebutton">
                        Add Module
                    </button>
                </form>
             -->
                
            </div>
        </div>
    </main>

</body>
</html>

