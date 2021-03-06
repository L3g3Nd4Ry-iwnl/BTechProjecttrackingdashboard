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
    <link rel="stylesheet" href="../css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@500&family=Oxygen:wght@300&display=swap"
        rel="stylesheet">

    <title>Add Module</title>
</head>

<body class="addmodule-body">
    <header class="addmodule-header">
        <h1>B.TECH PROJECT TRACKER</h1>
        <div class="logout-redirect">
            <form action="../html/about.html" method="GET">
                <button>
                    About
                </button>
            </form>
            <form action="../html/home.html" method="GET">
                <button>
                    Log-out
                </button>
            </form>
        </div>
    </header>
    <main class="addmodule-form">
        <img src="../images/module.jpg" height="auto" width="50%">
        <div class="form-details">
            <h1>Add Module</h1>
            <form class="addmodule-class">
                <div>
                    <div class="form-container">
                        <label for="module"><strong>Enter Module Name</strong></label>
                        <input type="text" placeholder="Enter your Module Name" name="module">
                    </div>
                </div>
                <button type="submit" value="">Submit</button>
            </form>
        </div>
    </main>
</body>

</html>