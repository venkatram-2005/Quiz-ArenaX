<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leaderboard</title>
    <link rel="stylesheet" type="text/css" href="leaderboard_styles.css">
</head>
<body>

    <div class="leaderboard-container">
        <h1>Leaderboard for <%= request.getParameter("subjectName") %></h1>

        <%
            String jdbcURL = "jdbc:mysql://localhost:3306/quizarena?allowPublicKeyRetrieval=true&useSSL=false";  
            String dbUser = "root";  
            String dbPassword = "valluri200513";  
            String subjectName = request.getParameter("subjectName");

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                
                // Create SQL query to fetch top scores for the selected subject
                String sql = "SELECT student_name, score FROM QuizResults WHERE subject_name = ? ORDER BY score DESC LIMIT 10";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, subjectName);
                rs = pstmt.executeQuery();
        %>

        <table class="leaderboard-table">
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>Student Name</th>
                    <th>Score</th>
                </tr>
            </thead>
            <tbody>
            <%
                int rank = 1; // Initialize rank
                // Loop through the result set and display the scores
                while (rs.next()) {
                    String studentName = rs.getString("student_name");
                    double score = rs.getDouble("score");
            %>
                <tr>
                    <td><%= rank++ %></td>
                    <td><%= studentName %></td>
                    <td><%= score %></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        

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
