<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/signup_styles.css">
    <script>
        function validatePassword() {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirm-password').value;
            const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;

            if (!passwordPattern.test(password)) {
                alert("Password must be at least 8 characters long and contain lowercase letters, uppercase letters, numbers, and special characters.");
                return false;
            }
            if (password !== confirmPassword) {
                alert("Passwords do not match.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <div class="container">
        <div class="image-section" style="display: flex; flex-direction: column; align-items: center; justify-content: center; text-align: center;">
            <img src="images/bg1.jpg" alt="Sign Up Image" style="width: 90%; display: block; margin: 0 auto;" />
            <a href="student_login.jsp" class="signup-image-link" style="margin-top: 10px; color: white;">
                I am already a member
            </a>
        </div>        
             
        <div class="form-section">
            <h1>Create an Account</h1>
            <form onsubmit="return validatePassword();" method="post" action="register">
                <div class="form-group">
                    <label for="name">Username:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="pass">Password:</label>
                    <input type="password" id="pass" name="pass" required>
                </div>
                <div class="form-group">
                    <label for="confirm-password">Confirm Password:</label>
                    <input type="password" id="confirm-password" name="confirm-password" required>
                </div>
                <div class="form-group">
                    <label for="contact">Mobile Number:</label>
                    <input type="text" id="contact" name="contact" required>
                </div>

                <div class="form-group" style="display: flex; align-items: center;">
                    <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" style="flex: 0 0 10%;" />
                    <label for="agree-term" class="label-agree-term" style="flex: 1; margin-left: 10px; color: white;">
                        I agree to all statements in <a href="#" class="term-service" style="color: white;">Terms of service</a>
                    </label>
                </div>
                
                
                <button type="submit">Sign Up</button>
            </form>
        </div>
    </div>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type = "text/javascript">
		var status = document.getElementById("status").value ;
		if(status == "success"){
			swal("Congrats", "Account Created Successfully","success") ;
		}
	</script>
</body>
</html>
