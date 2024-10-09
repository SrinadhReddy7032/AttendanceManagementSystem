package attendance;

import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class AttcheckServlet
 */
@WebServlet("/attcheck")
public class StudentAttCheckServlet extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAttCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","srinadh");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		try {
			String s1=request.getParameter("roll");
			PreparedStatement pstmt=con.prepareStatement("select * from attendance where rollnum=?");
			pstmt.setString(1, s1);
			ResultSet rs=pstmt.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=red text=yellow><center><h1>");
			if(rs.next()) {pw.println("WELLCOME NBKRIST STUDENTS");
			pw.println("<table border=20><tr><th>Roll Number</th><th>Attendance</th></tr>");
			pw.println("<tr><td>"+s1+"</td><td>"+rs.getInt(2)+"</td></tr>");
			pw.println("</table>");
			}
			else pw.println("INVALID ROLL NUMBER");
			pw.println("</h1></center></body></html>");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
