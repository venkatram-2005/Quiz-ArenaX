package registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
// import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("name") ;
		String useremail = request.getParameter("email") ;
		String userpassword = request.getParameter("pass") ;
		String usermobile = request.getParameter("contact") ;
		RequestDispatcher dispatcher = null ;
		Connection con = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver") ;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizarena?allowPublicKeyRetrieval=true&useSSL=false", "root", "valluri200513") ;
			PreparedStatement pst = con.prepareStatement("insert into users(username, useremail, userpassword, usermobile) values (?, ?, ?, ?)") ;
			
			pst.setString(1, username) ;
			pst.setString(2, useremail) ;
			pst.setString(3, userpassword) ;
			pst.setString(4, usermobile) ;
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp") ;
			if(rowCount > 0) {
				request.setAttribute("status", "success") ;
			}
			else {
				request.setAttribute("status", "failed") ;
			}
			
			dispatcher.forward(request, response) ;	
		}
		catch(Exception e){
			e.printStackTrace() ;
		}
		finally {
			// Close the connection only if it was successfully created
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}

}
