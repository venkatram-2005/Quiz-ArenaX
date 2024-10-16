<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Questions</title>
    <link rel="stylesheet" type="text/css" href="viewQuestions_styles.css">
</head>
<body>

    <div class="container">
        <h1>Quiz for <%= request.getParameter("subjectName") %></h1>

        <form method="post" action="submitQuiz.jsp"> <!-- Form to submit answers -->
            <%
                String jdbcURL = "jdbc:mysql://localhost:3306/quizarena?allowPublicKeyRetrieval=true&useSSL=false";  
                String dbUser = "root";  
                String dbPassword = "valluri200513";  
                String subjectName = request.getParameter("subjectName"); // Get subject name from URL

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    // Connect to the database
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                    
                    // Create SQL query to fetch questions for the selected subject
                    String sql = "SELECT * FROM " + subjectName + " ORDER BY RAND() LIMIT 10";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
            %>

            <div class="questions-container">
                <%
                	int questionNumber = 1;
                    // Loop through the result set and display the questions
                    while (rs.next()) {
                        int questionId = rs.getInt("id");
                %>
                <div class="question-box">
                    <div class="question">
                        <strong>Question <%= questionNumber %>:</strong> <%= rs.getString("question") %>
                    </div>
                    <div class="options">
                        <input type="radio" name="answer_<%= questionId %>" value="a" id="q<%= questionId %>_a"> 
                        <label for="q<%= questionId %>_a"><%= rs.getString("option_a") %></label><br>
                        <input type="radio" name="answer_<%= questionId %>" value="b" id="q<%= questionId %>_b"> 
                        <label for="q<%= questionId %>_b"><%= rs.getString("option_b") %></label><br>
                        <input type="radio" name="answer_<%= questionId %>" value="c" id="q<%= questionId %>_c"> 
                        <label for="q<%= questionId %>_c"><%= rs.getString("option_c") %></label><br>
                        <input type="radio" name="answer_<%= questionId %>" value="d" id="q<%= questionId %>_d"> 
                        <label for="q<%= questionId %>_d"><%= rs.getString("option_d") %></label><br>
                    </div>
                </div>
                <%
                	questionNumber++;
                    }
                %>
            </div>

            <input type="hidden" name="subjectName" value="<%= subjectName %>"> <!-- Pass subject name -->
            <input type="submit" value="Submit Quiz" class="submit-btn">
        </form>

        <%
                } catch (Exception e) {
                    out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
                } finally {
                    try { if (rs != null) rs.close(); } catch (SQLException e) {}
                    try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
                    try { if (conn != null) conn.close(); } catch (SQLException e) {}
                }
        %>

    </div>

</body>
</html>
