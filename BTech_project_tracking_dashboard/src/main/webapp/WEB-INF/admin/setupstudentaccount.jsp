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
    <title>Create Student Account</title>

</head>

<body class="account-set-body">
    <header class="account-set-header">
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

    <main class="register-form">
        <img src="../../images/register_account.jpg" height="auto" width="auto">
        <div class="form-details">
            <h1>Register Student</h1>
            <form class="register-form-class" action="../../admin/setup/student" method="POST">
                <div class="form-group">
                    <div class="form-container">
                        <label for="name"><strong>Name</strong></label>
                        <input type="text" placeholder="Enter Name" name="name">
                    </div>

                    <div class="form-container">
                        <label for="mobile"><strong>Mobile Number</strong></label>
                        <input type="tel" pattern="[6-9]{1}[0-9]{9}" placeholder="Enter Mobile Number" name="mobile">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="email"><strong>Email</strong></label>
                        <input type="email" placeholder="Enter Email" name="email">
                    </div>

                    <div class="form-container">
                        <label for="password"><strong>Password</strong></label>
                        <input type="password" placeholder="Enter Password" name="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="department"><strong>Department</strong></label>
                        <input type="text" placeholder="Enter Department" name="department">
                    </div>

                    <div class="form-container">
                        <label for="rollno"><strong>Roll Number</strong></label>
                        <input type="text" placeholder="Enter Roll Number" name="rollno">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-container">
                        <label for="section"><strong>Section</strong></label>
                        <input type="text" placeholder="Enter Section" name="section">
                    </div>
                    <div class="form-container">
                        <label for="academicgroup"><strong>Academic Year</strong></label>
                        <select name="academicgroup">
                            <option value="2018-2022">2018-2022</option>
                            <option value="2019-2023">2019-2023</option>
                            <option value="2020-2024">2020-2024</option>
                            <option value="2021-2025">2021-2025</option>
                        </select>
                    </div>
                </div>
                <button type="submit" value="">Submit</button>
            </form>
        </div>
    </main>
</body>

</html>