<%@page import="com.chargebee.app.bulk_operations.actions.ErrorfixBulkOperationAction"%>
<%
    ErrorfixBulkOperationAction act = (ErrorfixBulkOperationAction) ActionModel.getModel(request);
%>
<html>
    <body>
        <h3>This file is <%=act.getMeta().getViewPath()%>.</h3>
        <form>
              <%
              
              for (int i=0; i< act.header.size() ;i++) { %>
              <h6><%=act.header.get(i) %></h6>
              <input type="text" value="<%=act.values1.get(i) %>">
              <% } %>    
              
              <%@include file="partials/errorfixp.jspf" %> 
        </form>
    </body>
    </html>
    