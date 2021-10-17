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
    <title>Create Project</title>

</head>

<body class="create-proj-body">
    <header class="create-proj-header">
        <h1>B.TECH PROJECT TRACKER</h1>
        <div class="logout-redirect">
            <form action="../../aboutus" method="GET">
                <button>
                    About
                </button>
            </form>
            <form action="../../admin/logout" method="GET">
                <button>
                    Log-out
                </button>
            </form>
        </div>
    </header>

    <main class="project-form">
        <img src="../../images/create_prj.jpg" height="auto" width="auto">
        <div class="form-details">
            <h1>Create Project</h1>
            <form class="crtprj-form-class" action="../../admin/setup/project" method="POST">
                <div class="form-group">
                    <div class="form-container">
                        <label for="projectname"><strong>Project Name</strong></label>
                        <input type="text" placeholder="Enter Project Name" name="projectname">
                    </div>

                    <div class="form-container">
                        <label for="mentorid"><strong>Mentor ID</strong></label>
                        <input type="text" placeholder="Enter Mentor ID" name="mentorid">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="panelnumber"><strong>Panel Number</strong></label>
                        <input type="number" placeholder="Enter Panel Number" name="panelnumber">
                    </div>

                    <div class="form-container">
                        <label for="groupnumber"><strong>Group Number</strong></label>
                        <input type="text" placeholder="Enter Group Number" name="groupnumber">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="studentrollnumber1"><strong>Student 1 Roll Number </strong></label>
                        <input type="text" placeholder="Enter Roll Number" name="studentrollnumber1">
                    </div>

                    <div class="form-container">
                        <label for="studentrollnumber2"><strong>Student 2 Roll Number</strong></label>
                        <input type="text" placeholder="Enter Roll Number" name="studentrollnumber2">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="studentrollnumber3"><strong>Student 3 Roll Number </strong></label>
                        <input type="text" placeholder="Enter Roll Number" name="studentrollnumber3">
                    </div>

                    <div class="form-container">
                        <label for="studentrollnumber4"><strong>Student 4 Roll Number</strong></label>
                        <input type="text" placeholder="Enter Roll Number" name="studentrollnumber4">
                    </div>
                </div>
                <button type="submit" value="">Submit</button>
            </form>
        </div>
    </main>

</body>

</html>