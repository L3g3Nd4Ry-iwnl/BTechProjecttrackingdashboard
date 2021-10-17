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

    <title>Change Password</title>
</head>

<body class="chgpwd-body">
    <header class="chgpwd-header">
        <h1>B.TECH PROJECT TRACKER</h1>
        <div class="logout-redirect">
            <form action="../aboutus" method="GET">
                <button>
                    About us
                </button>
            </form>
            <form action="../student/logout" method="POST">
                <button>
                    Log-out
                </button>
            </form>
        </div>
    </header>
    <main class="chgpwd-form">
        <img src="../images/change_password.png" height="auto" width="50%">
        <div class="form-details">
            <h1>Change Password</h1>
            <form class="chgpwd-class" action="../student/changepass" method="POST">
             <div>
                    <div class="form-container">
                        <label for="password0"><strong>Enter your old password</strong></label>
                        <input type="password" placeholder="old password" name="password0">
                    </div>
                </div>
                <div>
                    <div class="form-container">
                        <label for="password1"><strong>Enter your new Password</strong></label>
                        <input type="password" placeholder="new password" name="password1">
                    </div>
                </div>
                <div>
                    <div class="form-container">
                        <label for="password2"><strong>Re-enter your new Password</strong></label>
                        <input type="password" placeholder="new password" name="password2">
                    </div>
                </div>
                <button type="submit" value="">Submit</button>
            </form>
        </div>
    </main>
</body>

</html>