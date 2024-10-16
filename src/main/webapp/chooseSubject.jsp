<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Subjects</title>
    <link rel="stylesheet" type="text/css" href="chooseSubject_styles.css">
</head>
<body>

<%
    // Database connection details
    String jdbcURL = "jdbc:mysql://localhost:3306/quizarena?allowPublicKeyRetrieval=true&useSSL=false";  
    String dbUser = "root";  
    String dbPassword = "valluri200513";  

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        // Connect to the database
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        
        // Create a SQL query to fetch all subjects
        String sql = "SELECT * FROM subjects";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
%>

<div class="table-container">
    <table>
        <thead>
            <tr>
               
                <th>Subject Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Loop through the result set and display the subjects
                while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("subjectname") %></td>
                <td>
                    <form action="viewQuestions.jsp" method="GET">
                        <input type="hidden" name="subjectId" value="<%= rs.getInt("id") %>" />
                        <input type="hidden" name="subjectName" value="<%= rs.getString("subjectname") %>" />
                        <button class="attempt-btn" type="submit">Attempt Quiz</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<%
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException e) {}
        try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
        try { if (conn != null) conn.close(); } catch (SQLException e) {}
    }
%>

</body>
</html>
