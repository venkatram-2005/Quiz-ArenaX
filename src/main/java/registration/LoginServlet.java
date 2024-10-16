package registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username") ;
		String userpassword = request.getParameter("password") ;
		HttpSession session = request.getSession() ;
		RequestDispatcher dispatcher = null ;
		if(request.getParameter("mode").equals("StudentLogin")) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver") ;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizarena?allowPublicKeyRetrieval=true&useSSL=false", "root", "valluri200513") ;
				PreparedStatement pst = con.prepareStatement("Select * from users where username = ? and userpassword = ?") ;
				pst.setString(1, username) ;
				pst.setString(2, userpassword) ; 
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					session.setAttribute("name", rs.getString("username")) ;
					dispatcher = request.getRequestDispatcher("index.jsp"); 
				}
				else {
					session.setAttribute("status", "failed") ;
					dispatcher = request.getRequestDispatcher("login.jsp"); 
				}
				dispatcher.forward(request, response) ;
				
			}
			catch(Exception e) {
				e.printStackTrace() ;
			}
		}
		else if(request.getParameter("mode").equals("TeacherLogin")) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver") ;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizarena?allowPublicKeyRetrieval=true&useSSL=false", "root", "valluri200513") ;
				PreparedStatement pst = con.prepareStatement("Select * from admins where username = ? and userpassword = ?") ;
				pst.setString(1, username) ;
				pst.setString(2, userpassword) ; 
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					session.setAttribute("name", rs.getString("username")) ;
					dispatcher = request.getRequestDispatcher("admin_home.jsp"); 
				}
				else {
					session.setAttribute("status", "failed") ;
					dispatcher = request.getRequestDispatcher("login.jsp"); 
				}
				dispatcher.forward(request, response) ;
				
			}
			catch(Exception e) {
				e.printStackTrace() ;
			}
		}
	}
}
