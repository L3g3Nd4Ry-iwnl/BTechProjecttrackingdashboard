<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <title>Admin Login</title>
</head>
<body class="login-body">
    <header class="login-header">
        <h1>B.TECH PROJECT TRACKER</h1>
        <div class="redirect">
            <form action="../" method="GET">
                <button>
                    Home
                </button>
            </form>
            <form action="../aboutus" method="GET">
                <button>
                    About us
                </button>
            </form>
            <form action="../faculty/login" method="GET">
                <button>
                    Faculty Login
                </button>
            </form>
            <form action="../student/login" method="GET">
                <button>
                    Student Login
                </button>
            </form>
        </div>
    </header>
    <main class="login-form">
        <img src="../images/project.jpg" height="auto" width="auto">
        <div class="form-details">	
            <h1>Welcome Back</h1>
            <form class="login-form-class" method="POST" action="../admin/login">
                <div>
                    <div class="form-container">
                        <label for="email"><strong>Email</strong></label>
                        <input type="email" placeholder="Enter your Email" name="email">
                    </div>
                </div>
                <div>
                    <div class="form-container">
                        <label for="password"><strong>Password</strong></label>
                        <input type="password" placeholder="Enter your Password" name="password">
                    </div>
                </div>
                <button type="submit" value="admin">Submit</button>
            </form>
            <label><strong>
            ${error}
            </strong></label>
            <label><strong>
            ${logout_msg}
            </strong></label>
        </div>
    </main>
</body>
</html>