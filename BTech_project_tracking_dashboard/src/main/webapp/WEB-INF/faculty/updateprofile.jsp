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
    <title>Update Profile</title>
</head>

<body class="update-body">
    <header class="update-header">
        <h1>B.TECH PROJECT TRACKER</h1>
        <div class="logout-redirect">
            <form action="../aboutus" method="GET">
                <button>
                    About
                </button>
            </form>
            <form action="../faculty/logout" method="POST">
                <button>
                    Log-out
                </button>
            </form>
        </div>
    </header>

    <main class="update-form">
        <img src="../images/update.jpg" height="auto" width="50%">
        <div class="form-details">
            <h1>Update Profile</h1>
            <form class="update-class" action="../faculty/profile" method="POST">
                <div class="form-group">
                    <div class="form-container">
                        <label for="name"><strong>Name</strong></label>
                        <label type="text" name="name">${name}</label>
                    </div>

                    <div class="form-container">
                        <label for="department"><strong>Department</strong></label>
                        <label type="text" name="department">${department}</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-container">
                        <label for="email"><strong>Email</strong></label>
                        <label type="email" name="email">${email}</label>
                    </div>

                    <div class="form-container">
                        <label for="fid"><strong>Faculty ID</strong></label>
                        <label type="text" name="fid">${fid}</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-container">
                        <label for="mobile"><strong>Mobile Number</strong></label>
                        <input type="tel" pattern="[6-9]{1}[0-9]{9}" placeholder= ${mobile} name="mobile">
                    </div>
                </div>
                <button type="submit" value="profileupdate">Submit</button>
            </form>

            <form action="../faculty/changepass" method="GET" class="changepwd">
                <button>Change Password</button>
            </form>

        </div>

    </main>
</body>

</html>