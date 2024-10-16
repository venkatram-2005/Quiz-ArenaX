<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Subjects List</title>
    <link rel="stylesheet" type="text/css" href="css/admin_deleteSubject_styles.css"> <!-- External CSS -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <h2 class="page-heading">Subjects List</h2> <!-- Heading at the top -->
    <div class="container"> <!-- Centered container -->
        <table>
            <tr>
                <th>Subject Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="subject" items="${subjectsList}">
                <tr>
                    <td>${subject.subjectname}</td>
                    <td>
                        <a href="ServletControllerFile?subjectToDelete=${subject.subjectname}" class="btn btn-danger">Delete</a>
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
