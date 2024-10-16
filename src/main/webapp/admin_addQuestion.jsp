<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Question</title>
    <link rel="stylesheet" href="css/admin_addQuestion_styles.css">
</head>
<body>
    <div class="container">
        <h1>Add Question for ${param.subjectToAddQuestion}</h1>
        <form action="QuestionServlet" method="post">
            <!-- Hidden field to store the subject name -->
        	<input type="hidden" name="subjectToAddQuestion" value="${param.subjectToAddQuestion}">

            <div class="form-group">
                <label for="question">Question:</label>
                <input type="text" id="question" name="question" required>
            </div>
            
            <div class="form-group">
                <label for="optionA">Option A:</label>
                <input type="text" id="optionA" name="optionA" required>
            </div>
            
            <div class="form-group">
                <label for="optionB">Option B:</label>
                <input type="text" id="optionB" name="optionB" required>
            </div>
            
            <div class="form-group">
                <label for="optionC">Option C:</label>
                <input type="text" id="optionC" name="optionC" required>
            </div>
            
            <div class="form-group">
                <label for="optionD">Option D:</label>
                <input type="text" id="optionD" name="optionD" required>
            </div>
            
            <div class="form-group">
                <label for="answerOption">Correct Answer:</label>
                <select id="answerOption" name="answerOption" required>
                    <option value="a">A</option>
                    <option value="b">B</option>
                    <option value="c">C</option>
                    <option value="d">D</option>
                </select>
            </div>
            
            <button type="submit">Submit Question</button>
        </form>
    </div>
    <!-- SweetAlert integration -->
    <input type="hidden" id="status" name="status" value="${requestScope.status}">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status === "success") {
            swal("Success", "Question added successfully!", "success");
        } else if (status === "exists") {
            swal("Error", "Question already exists!", "error");
        } else if (status === "error") {
            swal("Error", "An error occurred while processing!", "error");
        }
    </script>
</body>
</html>
