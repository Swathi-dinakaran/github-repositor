import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class QueryServlet extends HttpServlet {  
 
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
 
      Connection conn = null;
      Statement stmt = null;
      try {
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop?useSSL=false", "admin", "swathi"); 
          stmt = conn.createStatement();
 
         String sqlStr = "select * from books where author = "
              + "'" + request.getParameter("author") + "'"
              + " and qty > 0 order by price desc";
 
         out.println("<html><head><title>Query Response</title></head><body>");
         out.println("<h3>Thank you for your query.</h3>");
         out.println("<p>You query is: " + sqlStr + "</p>"); 
         ResultSet rset = stmt.executeQuery(sqlStr);  
 
         int count = 0;
         while(rset.next()) {
            out.println("<p>" + rset.getString("author")
                 + ", " + rset.getString("title")
                 + ", $" + rset.getDouble("price") + "</p>");
            count++;
         }
         out.println("<p>==== " + count + " records found =====</p>");
         out.println("</body></html>");
     } catch (SQLException ex) {
        ex.printStackTrace();
     } finally {
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




