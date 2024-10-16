<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.Question" %>
<html>
<head>
    <title>Edit Question</title>
    <link rel="stylesheet" href="css/admin_editQuestion_styles.css"> 
</head>
<body>
    <div class="container">
        <h1>Edit Question for <%= request.getAttribute("subjectName") %></h1>
        <form action="QuestionServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="questionId" value="<%= ((Question) request.getAttribute("question")).getId() %>">
            <input type="hidden" name="subjectName" value="<%= request.getAttribute("subjectName") %>">
            
            <label for="question">Question:</label>
            <textarea id="question" name="question" required><%= ((Question) request.getAttribute("question")).getQuestion() %></textarea><br>

            <label for="optionA">Option A:</label>
            <input type="text" id="optionA" name="optionA" value="<%= ((Question) request.getAttribute("question")).getOptionA() %>" required><br>

            <label for="optionB">Option B:</label>
            <input type="text" id="optionB" name="optionB" value="<%= ((Question) request.getAttribute("question")).getOptionB() %>" required><br>

            <label for="optionC">Option C:</label>
            <input type="text" id="optionC" name="optionC" value="<%= ((Question) request.getAttribute("question")).getOptionC() %>" required><br>

            <label for="optionD">Option D:</label>
            <input type="text" id="optionD" name="optionD" value="<%= ((Question) request.getAttribute("question")).getOptionD() %>" required><br>

            <label for="answerOption">Correct Answer Option:</label>
            <select id="answerOption" name="answerOption" required>
                <option value="a" <%= ((Question) request.getAttribute("question")).getAnswerOption().equals("a") ? "selected" : "" %>>A</option>
                <option value="b" <%= ((Question) request.getAttribute("question")).getAnswerOption().equals("b") ? "selected" : "" %>>B</option>
                <option value="c" <%= ((Question) request.getAttribute("question")).getAnswerOption().equals("c") ? "selected" : "" %>>C</option>
                <option value="d" <%= ((Question) request.getAttribute("question")).getAnswerOption().equals("d") ? "selected" : "" %>>D</option>
            </select><br>

            <input type="submit" value="Update Question">
        </form>
    </div>
</body>

</html>
