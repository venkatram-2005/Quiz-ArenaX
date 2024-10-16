<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Login</title>
    <link rel="stylesheet" href="css/teacher_login_styles.css">
</head>
<body>
    <div class="container">
        <div class="image-section">
            <img src="images/teacher.jpg" alt="Teacher Login" class="card-image"> <!-- Replace with the correct image path -->
        </div>
        <div class="form-section">
            <h1>Teacher Login</h1>
            <form action="LoginServlet" method="post">
            <input type="hidden" id="mode" name="mode" value="TeacherLogin">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit">Login</button>
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
