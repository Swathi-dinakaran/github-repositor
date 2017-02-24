import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
 
      Connection conn = null;
      Statement stmt = null;
      try {
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/selfserviceportal?useSSL=false", "admin", "swathi"); 
         stmt = conn.createStatement();
         String sqlStr = "select * from signup where email=\""+request.getParameter("e")+"\" and password= \""+request.getParameter("pass")+"\"";
         out.println("<html><head><title> Login </title></head><body>");
         
          ResultSet rs=stmt.executeQuery(sqlStr);

          if(rs.next())
          {
         	out.println("login successful</br>");
         	out.println("Welcome!</br>");
         	Cookie ck=new Cookie("name",rs.getString("e"));  
            response.addCookie(ck);  
            out.println("cookie "+ck.getName()+" added");
            HttpSession session=request.getSession();  
            session.setAttribute("name",request.getParameter("e"));  
            out.println("session with id : "+session.getId()+" added");
            Thread.sleep(500);
            RequestDispatcher view = request.getRequestDispatcher("/edit.html");
            view.forward(request, response);
          }

         else
         {
         	out.println("login unsuccessful");
         }
         out.println("</body></html>");
     } catch (SQLException ex) {
        ex.printStackTrace();
     }
     /*catch (InterruptedException ex) {
        ex.printStackTrace();
     }*/
      finally {
        out.close();  // Close the output writer
        try {
           // Step 5: Close the resources
           if (stmt != null) stmt.close();
           if (conn != null) conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
     }
   }
}