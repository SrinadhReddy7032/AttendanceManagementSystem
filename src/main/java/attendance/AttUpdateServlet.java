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
* Servlet implementation class AttServlet
*/
@WebServlet("/attup")
public class AttUpdateServlet extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AttUpdateServlet() {
      super();
      // TODO Auto-generated constructor stub
  }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config)  {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String s1=request.getParameter("rollnum");
			String s2=request.getParameter("atds"); 
			PreparedStatement pstmt2=con.prepareStatement("select * from attendance where rollnum=? ");
			pstmt2.setString(1,s1);
			ResultSet rs=pstmt2.executeQuery();
			if(rs.next()) {
			PreparedStatement pstmt1=con.prepareStatement("update attendance set atds=? where rollnum=?");
			pstmt1.setString(1,s2);
			pstmt1.setString(2,s1);
			pstmt1.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=blue text=red><center>");
			pw.println("<h1>....................Updated Attendance successfully................</h1>");
			pw.println("</center></body></html>");
			}
			else {
			PreparedStatement pstmt=con.prepareStatement("insert into attendance values(?,?)");
			pstmt.setString(1,s1);
			pstmt.setString(2,s2);
			pstmt.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=blue text=red><center>");
			pw.println("<h1>....................Uploaded Attendance successfully................</h1>");
			pw.println("</center></body></html>");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
