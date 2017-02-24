import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class SignupServlet extends HttpServlet {  
 
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
 
      Connection conn = null;
      Statement stmt = null;
      try {
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/selfserviceportal?useSSL=false", "admin", "swathi"); 
         stmt = conn.createStatement();
         String sqlStr = "insert into signup values(\""+request.getParameter("firstname")+"\",\""+request.getParameter("lastname")+"\",\""+request.getParameter("e")+"\",\""+request.getParameter("pass")+"\")";
         out.println("<html><head><title>Query Response</title></head><body>");
         
         
          if((request.getParameter("e").equals(request.getParameter("ce"))) && (request.getParameter("pass").equals(request.getParameter("cpass"))))
          {
            out.println("<h3>Thank you for your sign up.</h3>");
            out.println("<p> query is: " + sqlStr + "</p>"); 
            stmt.executeUpdate(sqlStr);  
            out.println("signup successful");
            
          }
          else
          {
            out.println(request.getParameter("e").equals(request.getParameter("ce")));

            out.println(request.getParameter("pass").equals(request.getParameter("cpass")));
            out.println("signup unsuccessful");
            Thread.sleep(500);
            RequestDispatcher view = request.getRequestDispatcher("/signup.html");
            view.forward(request, response);
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


