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

import bean.Subject;
import dBOperations.DBOperations;

/**
 * Servlet implementation class ServletControllerFile
 */
@WebServlet("/ServletControllerFile")
public class ServletControllerFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("displaySubjectsToDelete")!=null) {
			try {
				displaySubjectsToDelete(request, response) ;
			}
			catch(Exception e) {
				e.printStackTrace();
				response.getWriter().println("<h1>There seems to be an error in ServletController functionality</h1>") ;
			}
		}
		else if(request.getParameter("subjectToDelete") != null) {
			String subjectname = request.getParameter("subjectToDelete");
			try{
				deleteSubject(subjectname, request, response) ;
                request.setAttribute("status", "success");
			}catch(Exception e) {
				e.printStackTrace() ;
				response.getWriter().println("<h1>Error occurred while deleting subject</h1>");
				request.setAttribute("status", "error");
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectname = request.getParameter("subjectname");

        // Database connection and operations
        try {
            DBOperations op = new DBOperations(); // Instantiate your DBOperations class
            
            // Check if the subject already exists
            if (op.isSubjectExists(subjectname)) {
                // If subject already exists, send back an error message
            	request.setAttribute("status", "exists");  // Set error status
            } else {
                // Insert the new subject into the database
                op.addSubject(subjectname); // Add method in DBOperations to insert subject

                //  display success message
                request.setAttribute("status", "success");  // Set success status
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_addSubject.jsp");  
            dispatcher.forward(request, response);
            
        } catch (SQLException | ClassNotFoundException e) {
        	request.setAttribute("status", "error");
            throw new ServletException("Database error", e);
            
        }
    }
	
	private void displaySubjectsToDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException{
		DBOperations op = new DBOperations() ;
		List<Subject> subjectsList = op.getSubjectNames() ;
		request.setAttribute("subjectsList", subjectsList) ;
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_deleteSubject.jsp");
		dispatcher.forward(request, response) ;
	}
	
	private void deleteSubject(String subjectname, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException{
		DBOperations op = new DBOperations() ;
		op.deleteSubject(subjectname);
		displaySubjectsToDelete(request, response) ;
	}
}
