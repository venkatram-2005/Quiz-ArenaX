<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Subjects List</title>
    <link rel="stylesheet" href="css/admin_viewSubjects_styles.css">
</head>
<body>
    <div class="container"> <!-- Added a container to center content -->
       <h2 style="font-family: 'Times New Roman', Times, serif;">Subjects List</h2>
        <table>
            <tr>
                <th>Subject Name</th>
                <th>Operations</th>
            </tr>
            
            <c:forEach var="subject" items="${subjectsList}">
                <tr>
                    <td>${subject.subjectname}</td>
                    <td>
                        <a href="admin_addQuestion.jsp?subjectToAddQuestion=${subject.subjectname}" class="btn">Add Question</a>
                        <a href="QuestionServlet?subjectToDeleteQuestion=${subject.subjectname}" class="btn">Delete Questions</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
