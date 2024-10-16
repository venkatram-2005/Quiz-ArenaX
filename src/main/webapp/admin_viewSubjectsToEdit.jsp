<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Subjects List</title>
    <link rel="stylesheet" type="text/css" href="css/admin_viewSubjectsToEdit_styles.css"> <!-- Link to the external CSS -->
</head>
<body>
    <div class="container"> <!-- Container for styling -->
        <h1>Subjects List</h1> <!-- Added heading -->
        <table>
            <tr>
                <th>Subject Name</th>
                <th>Actions</th> <!-- Header for actions column -->
            </tr>

            <c:forEach var="subject" items="${subjectsList}">
                <tr>
                    <td>${subject.subjectname}</td>
                    <td>
                        <a href="QuestionServlet?subjectToEditQuestion=${subject.subjectname}" class="btn">Edit Questions</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
