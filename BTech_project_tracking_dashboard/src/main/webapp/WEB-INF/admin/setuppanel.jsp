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
    <title>Create new faculty panel</title>

</head>

<body class="panel-body">
    <header class="panel-header">
        <h1>B.TECH PROJECT TRACKER</h1>
        <div class="logout-redirect">
            <form action="../../aboutus" method="GET">
                <button>
                    About
                </button>
            </form>
            <form action="../../admin/logout" method="POST">
                <button>
                    Log-out
                </button>
            </form>
        </div>
    </header>

    <main class="panel-form">
        <img src="../images/panel.jpg" height="auto" width="auto">
        <div class="form-details">
            <h1>Create Panel</h1>
            <form class="panel-form-class" action="../../admin/setup/panel" method="POST">
                <div class="form-group">
                    <div class="form-container">
                        <label for="panelno"><strong>Panel Number</strong></label>
                        <input type="number" placeholder="Enter Panel Number" name="panelno">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="faculty1"><strong>Faculty ID 1</strong></label>
                        <input type="text" placeholder="faculty ID 1" name="faculty1">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="faculty2"><strong>Faculty ID 2</strong></label>
                        <input type="text" placeholder="faculty ID 2" name="faculty2">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="faculty3"><strong>Faculty ID 3</strong></label>
                        <input type="text" placeholder="faculty ID 3" name="faculty3">
                    </div>
                </div>
                <button type="submit" value="">Submit</button>
            </form>
        </div>

    </main>

</body>

</html>