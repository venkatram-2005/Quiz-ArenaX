<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Login</title>
    <link rel="stylesheet" href="css/student_login_styles.css">
</head>
<body>
    <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
    <div class="container">
        <div class="image-section">
            <img src="images/studnet.jpg" alt="Student Login" class="card-image">
        </div>
        <div class="form-section">
            <h1>Student Login</h1>
            <form action="LoginServlet" method="post">
            <input type="hidden" id="mode" name="mode" value="StudentLogin">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="button-container" style="display: flex; justify-content: space-between; margin-top: 10px;">
                    <button>
                        <a href="registration.jsp" style="text-decoration: none; color: white;">Create a New Account</a>
                    </button>
                    <button type="submit">Login</button>
                </div>
            </form>
        </div>
    </div>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type = "text/javascript">
		var status = document.getElementById("status").value ;
		if(status == "failed"){
			swal("Sorry", "Wrong Username or Password","error") ;
		}
	</script>
</body>
</html>
