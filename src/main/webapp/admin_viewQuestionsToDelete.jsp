<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Questions</title>
    <link rel="stylesheet" type="text/css" href="css/admin_viewQuestionsToDelete_styles.css"> <!-- Link to the external CSS -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <div class="container"> <!-- Added container for styling -->
        <h1>Questions for ${subjectName}</h1>
        <table>
            <tr>
                <th>Question</th>
                <th>Options</th>
                <th>Correct Answer</th>
                <th>Action</th>
            </tr>
            
            <c:if test="${empty questionsList}">
                <tr>
                    <td colspan="4">No questions available for this subject.</td>
                </tr>
            </c:if>
            
            <c:forEach var="question" items="${questionsList}">
                <tr>
                    <td>${question.question}</td>
                    <td>
                        A: ${question.optionA}<br>
                        B: ${question.optionB}<br>
                        C: ${question.optionC}<br>
                        D: ${question.optionD}
                    </td>
                    <td>${question.answerOption}</td>
                    <td>
                        <form action="QuestionServlet" method="post" style="display:inline;">
                            <input type="hidden" name="questionId" value="${question.id}">
                            <input type="hidden" name="subjectName" value="${subjectName}">
                            <button type="submit" name="action" value="delete">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <input type="hidden" id="status" value="${requestScope.status}">
    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status === "success") {
            swal("Success", "Question deleted successfully!", "success");
        } else if (status === "error") {
            swal("Error", "An error occurred while deleting the question!", "error");
        }
    </script>
</body>
</html>
