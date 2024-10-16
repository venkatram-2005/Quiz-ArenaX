package dBOperations;

import java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Question;
import bean.Subject;

public class DBOperations {
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver") ;
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizarena", "root", "valluri200513");
		
		return connect ;
	}
	
	public List<Subject> getSubjectNames() throws ClassNotFoundException, SQLException{
		String query = "Select * from subjects" ;
		List<Subject> subjectsList = new ArrayList<>() ;
		Connection connect = getConnection() ;
		Statement stmt = connect.createStatement() ;
        ResultSet res = stmt.executeQuery(query);
        
        while(res.next()) {
        	String id = res.getString(1) ;
        	String subjectname = res.getString(2) ;
        	
        	Subject s = new Subject(id, subjectname) ;
        	subjectsList.add(s) ;
        }
        return subjectsList ;
	}

	public void deleteSubject(String subjectname)throws ClassNotFoundException, SQLException {
		Connection connect = getConnection() ;
		String query = "Delete From Subjects Where subjectname = ?" ;
		PreparedStatement p = connect.prepareStatement(query) ;
		p.setString(1, subjectname) ;
		p.executeUpdate() ;
		
		String q2 = "DROP TABLE IF EXISTS " + subjectname ;
		Statement st = connect.createStatement() ;
		st.executeUpdate(q2) ;
	}
	
	// Method to check if a subject already exists in the database
    public boolean isSubjectExists(String subjectname) throws ClassNotFoundException, SQLException {
        String query = "SELECT COUNT(*) FROM subjects WHERE subjectname = ?";
        Connection connect = getConnection();
        PreparedStatement stmt = connect.prepareStatement(query);
        stmt.setString(1, subjectname);
        ResultSet resultSet = stmt.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) > 0; // Return true if count > 0, meaning subject exists
    }

    // Method to add a new subject to the subjects table
    public void addSubject(String subjectname) throws ClassNotFoundException, SQLException {
    	subjectname = sanitizeSubjectName(subjectname); 
        String insertQuery = "INSERT INTO subjects (subjectname) VALUES (?)";
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + sanitizeSubjectName(subjectname) + " ("
            + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + "question TEXT NOT NULL, "
            + "option_a VARCHAR(255) NOT NULL, "
            + "option_b VARCHAR(255) NOT NULL, "
            + "option_c VARCHAR(255) NOT NULL, "
            + "option_d VARCHAR(255) NOT NULL, "
            + "answer_option ENUM('a', 'b', 'c', 'd') NOT NULL"
            + ")";

        try (Connection connect = getConnection(); 
             PreparedStatement insertStmt = connect.prepareStatement(insertQuery);
             Statement createStmt = connect.createStatement()) {
             
            // Insert the subject name into the subjects table
            insertStmt.setString(1, subjectname);
            insertStmt.executeUpdate();

            // Create a new table for the subject
            createStmt.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (log them or rethrow)
        }
    }
    // Sanitize subject name to make it a valid SQL identifier
    private String sanitizeSubjectName(String subjectname) {
        // Replace spaces with underscores and remove invalid characters
        return subjectname.replaceAll("[^a-zA-Z0-9_]", "_");
    }
    
    public void addQuestion(Question question) throws SQLException, ClassNotFoundException {
        String subjectTable = sanitizeSubjectName(question.getSubject());
        String query = "INSERT INTO " + subjectTable + " (question, option_a, option_b, option_c, option_d, answer_option) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connect = getConnection(); 
             PreparedStatement stmt = connect.prepareStatement(query)) {
             
            stmt.setString(1, question.getQuestion());
            stmt.setString(2, question.getOptionA());
            stmt.setString(3, question.getOptionB());
            stmt.setString(4, question.getOptionC());
            stmt.setString(5, question.getOptionD());
            stmt.setString(6, question.getAnswerOption());
            
            stmt.executeUpdate();  // Execute the query to insert the question
        }
    }

    public List<Question> getQuestionsBySubject(String subjectName) throws SQLException, ClassNotFoundException {
        List<Question> questions = new ArrayList<>();
        sanitizeSubjectName(subjectName);
        String query = "SELECT * FROM " + subjectName;  // Adjust this query as needed based on your table structure

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOptionA(rs.getString("option_a"));
                question.setOptionB(rs.getString("option_b"));
                question.setOptionC(rs.getString("option_c"));
                question.setOptionD(rs.getString("option_d"));
                question.setAnswerOption(rs.getString("answer_option"));
                questions.add(question);
            }
            for (Question question : questions) {
                System.out.println("Question: " + question.getQuestion());
                System.out.println("Option A: " + question.getOptionA());
                System.out.println("Option B: " + question.getOptionB());
                System.out.println("Option C: " + question.getOptionC());
                System.out.println("Option D: " + question.getOptionD());
                System.out.println("Answer Option: " + question.getAnswerOption());
            }
            return questions;
        }
    }

    public boolean deleteQuestionById(int questionId, String subjectName) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + subjectName + " WHERE id = ?"; // subjectName is the table, and id is the question's primary key
        
        try (Connection connection = getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, questionId);  // Set the questionId in the query
            int rowsAffected = stmt.executeUpdate(); // Execute the delete query
            
            // Return true if one or more rows were affected (meaning the question was deleted)
            return rowsAffected > 0;
        }
    }
    
    public Question getQuestionById(int questionId, String subjectName) throws SQLException, ClassNotFoundException {
        Question question = null;
        String query = "SELECT * FROM " + subjectName + " WHERE id = ?";  // Prepare query to get the question

        try (Connection connection = getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(query)) {
             
            stmt.setInt(1, questionId);  // Set the question ID in the query
            ResultSet rs = stmt.executeQuery();  // Execute the query

            if (rs.next()) {
                // Create a new Question object and populate it with data from the result set
                question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setOptionA(rs.getString("option_a"));
                question.setOptionB(rs.getString("option_b"));
                question.setOptionC(rs.getString("option_c"));
                question.setOptionD(rs.getString("option_d"));
                question.setAnswerOption(rs.getString("answer_option"));
            }
        }

        return question;  // Return the question object
    }
    
    public boolean updateQuestion(Question question) throws SQLException, ClassNotFoundException {
        // Sanitize the subject name to prevent SQL injection attacks
        String subjectTable = sanitizeSubjectName(question.getSubject());

        // SQL query to update the question based on its ID
        String query = "UPDATE `" + subjectTable + "` SET question = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, answer_option = ? WHERE id = ?";

        // Using try-with-resources to automatically close the connection and statement
        try (Connection connection = getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            // Set the parameters for the SQL query
            stmt.setString(1, question.getQuestion());       // Set the question text
            stmt.setString(2, question.getOptionA());        // Set option A
            stmt.setString(3, question.getOptionB());        // Set option B
            stmt.setString(4, question.getOptionC());        // Set option C
            stmt.setString(5, question.getOptionD());        // Set option D
            stmt.setString(6, question.getAnswerOption());   // Set the correct answer option
            stmt.setInt(7, question.getId());                // Set the question ID for the WHERE clause
            
            // Execute the update query and return true if the update was successful
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if at least one row was updated
        }
    }





}
