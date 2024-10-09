package attendance;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) 
	{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","srinadh");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
			String s1=request.getParameter("fname");
			String s2=request.getParameter("lname");
			String s3=request.getParameter("uname");
			String s4=request.getParameter("pword"); 
			PreparedStatement pstmt=con.prepareStatement("insert into registration values(?,?,?,?)");
			pstmt.setString(1,s1);
			pstmt.setString(2,s2);
			pstmt.setString(3,s3);
			pstmt.setString(4,s4);
			pstmt.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=blue text=red><center>");
			pw.println("<h1>....................You have registred successfully................</h1>");
			pw.println("<a href=StudentLogin.html>Login</a>");
			pw.println("</center></body></html>");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
