<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Subject</title>
    <link rel="stylesheet" type="text/css" href="css/admin_addSubject_styles.css">
</head>
<body>
    <div class="container">
        <h1>Add Subject</h1>
        <form action="ServletControllerFile" method="post">
            <div class="form-group">
                <label for="subjectname">Subject Name:</label>
                <input type="text" id="subjectname" name="subjectname" required>
                <small class="warning">* Subject name can't be changed and must be unique.</small><br>
            	<small class="warning">* Avoid Using characters other than 'A-Z', 'a-z', '0-9', '_' </small>
            </div>
            <button type="submit">Add Subject</button>
        </form>
        <br><br>
        <a href="admin_home.jsp" class="button">Go to Home</a>
    </div>
    <input type="hidden" id="status" name="status" value="${requestScope.status}">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="text/javascript">
		var status = document.getElementById("status").value;
	    if (status === "success") {
	        swal("Success", "Subject added successfully!", "success");
	    } else if (status === "exists") {
	        swal("Error", "Subject already exists!", "error");
	    } else if (status === "error") {
	        swal("Error", "An error occurred while processing!", "error");
	    }
	</script>
</body>
</html>
