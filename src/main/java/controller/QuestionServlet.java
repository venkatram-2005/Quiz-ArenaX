package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bean.Question;
import bean.Subject;
import dBOperations.DBOperations;

/**
 * Servlet implementation class ServletControllerFile
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("displaySubjectsToAdd")!=null) {
			try {
				displaySubjectsToAdd(request, response) ;
			}
			catch(Exception e) {
				e.printStackTrace();
				response.getWriter().println("<h1>There seems to be an error in ServletController functionality</h1>") ;
			}
		}
		else if(request.getParameter("displaySubjectsToEdit")!=null) {
			try {
				displaySubjectsToEdit(request, response) ;
			}
			catch(Exception e) {
				e.printStackTrace();
				response.getWriter().println("<h1>There seems to be an error in ServletController functionality</h1>") ;
			}
		}
		else if(request.getParameter("subjectToDeleteQuestion")!=null) {
			String subjectName = request.getParameter("subjectToDeleteQuestion");
	        DBOperations db = new DBOperations();
	        try {
	            // Get questions for the specified subject
	            List<Question> questions = db.getQuestionsBySubject(subjectName);
	            request.setAttribute("questionsList", questions);
	            request.setAttribute("subjectName", subjectName);
	            
	            // Forward to the JSP page to display questions
	            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_viewQuestionsToDelete.jsp");
	            dispatcher.forward(request, response);
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            response.sendRedirect("home.jsp"); // Redirect to an error page or handle as needed
	        }
		}
		else if (request.getParameter("subjectToEditQuestion")!=null) {
	        String subjectName = request.getParameter("subjectToEditQuestion");
	        
	        try {
	            DBOperations dbOps = new DBOperations();
	            List<Question> questions = dbOps.getQuestionsBySubject(subjectName); // Fetch questions for the selected subject
	            request.setAttribute("questions", questions);
	            request.setAttribute("subjectName", subjectName); // Pass the subject name to the JSP
	            
	            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_viewQuestionsToEdit.jsp"); // Forward to the edit questions page
	            dispatcher.forward(request, response);
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            request.setAttribute("status", "error");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp"); // Forward to an error page
	            dispatcher.forward(request, response);
	        }
	    }
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the subjectToAddQuestion parameter is present
        if (request.getParameter("subjectToAddQuestion") != null) {
            String subject = request.getParameter("subjectToAddQuestion");  // Get subject name
            String questionText = request.getParameter("question");
            String optionA = request.getParameter("optionA");
            String optionB = request.getParameter("optionB");
            String optionC = request.getParameter("optionC");
            String optionD = request.getParameter("optionD");
            String answerOption = request.getParameter("answerOption");

            // Create a Question object to hold the data
            Question question = new Question(subject, questionText, optionA, optionB, optionC, optionD, answerOption);

            // Database operations and status setting
            DBOperations dbOperations = new DBOperations();
            try {        
                dbOperations.addQuestion(question);  // Method to insert question into DB
                request.setAttribute("status", "success");
            } catch (SQLException | ClassNotFoundException e) {
            	request.setAttribute("status", "error");
                e.printStackTrace();
            }

            // Forward the status to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_addQuestion.jsp");
            dispatcher.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("delete")) {
        	try {
                // Retrieve the questionId and subjectName from the request
                int questionId = Integer.parseInt(request.getParameter("questionId"));
                String subjectName = request.getParameter("subjectName");
                
                // Call DBOperations to delete the question
                DBOperations dbOps = new DBOperations();
                boolean isDeleted = dbOps.deleteQuestionById(questionId, subjectName);
                
                if (isDeleted) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "error");
                }
             // Refetch the updated list of questions after deletion
                List<Question> updatedQuestionsList = dbOps.getQuestionsBySubject(subjectName);
                request.setAttribute("questionsList", updatedQuestionsList);  // Update questionsList
                request.setAttribute("subjectName", subjectName);  // Retain subject name
            } catch (SQLException | ClassNotFoundException e) {
                // Handle the SQLException and ClassNotFoundException
                e.printStackTrace();
                request.setAttribute("status", "error");
            }
        	// Forward the status to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_viewQuestionsToDelete.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("action").equals("edit")) {
		    int questionId = Integer.parseInt(request.getParameter("questionId"));
		    String subjectName = request.getParameter("subjectName");

		    try {
		        DBOperations dbOps = new DBOperations();
		        Question question = dbOps.getQuestionById(questionId, subjectName); // Fetch the question by ID

		        request.setAttribute("question", question);
		        request.setAttribute("subjectName", subjectName);
		        
		        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_editQuestion.jsp"); // JSP to edit the question
		        dispatcher.forward(request, response);
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		        request.setAttribute("status", "error");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
		        dispatcher.forward(request, response);
		    }
		}
        else if (request.getParameter("action").equals("update")) {
            try {
                int questionId = Integer.parseInt(request.getParameter("questionId"));
                String subjectName = request.getParameter("subjectName");
                String questionText = request.getParameter("question");
                String optionA = request.getParameter("optionA");
                String optionB = request.getParameter("optionB");
                String optionC = request.getParameter("optionC");
                String optionD = request.getParameter("optionD");
                String answerOption = request.getParameter("answerOption");

                Question question = new Question();
                question.setId(questionId);
                question.setSubject(subjectName);
                question.setQuestion(questionText);
                question.setOptionA(optionA);
                question.setOptionB(optionB);
                question.setOptionC(optionC);
                question.setOptionD(optionD);
                question.setAnswerOption(answerOption);

                DBOperations dbOps = new DBOperations();
                boolean updated = dbOps.updateQuestion(question); // Update the question
                
                if (updated) {
                    response.sendRedirect("QuestionServlet?action=editQuestions&subjectToEditQuestion=" + subjectName); // Redirect back to the edit page
                } else {
                    request.setAttribute("status", "error");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("status", "error");
                RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
	
	private void displaySubjectsToAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException{
		DBOperations op = new DBOperations() ;
		List<Subject> subjectsList = op.getSubjectNames() ;
		request.setAttribute("subjectsList", subjectsList) ;
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_viewSubjects.jsp");
		dispatcher.forward(request, response) ;
	}
	private void displaySubjectsToEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException{
		DBOperations op = new DBOperations() ;
		List<Subject> subjectsList = op.getSubjectNames() ;
		request.setAttribute("subjectsList", subjectsList) ;
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_viewSubjectsToEdit.jsp");
		dispatcher.forward(request, response) ;
	}
}
