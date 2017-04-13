<%-- 
    Document   : project
    Created on : Mar 13, 2017, 11:47:10 AM
    Author     : swathi
--%>
<%@page import="com.chargebee.framework.util.CSVUtils"%>
<%@page import="org.jooq.tools.csv.CSVParser"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="com.chargebee.framework.metamodel.EnumColumn"%>
<%@page import="com.chargebee.framework.metamodel.BooleanCol"%>
<%@page import="com.chargebee.framework.metamodel.TimestampCol"%>
<%@page import="com.chargebee.framework.metamodel.StringCol"%>
<%@page import="com.chargebee.framework.metamodel.Column"%>
<%@page import="com.chargebee.framework.api.InputParam"%>
<%@page import="com.chargebee.app.bulk_operations.actions.IndexBulkOperationAction"%>
<%@page import="com.chargebee.framework.env.Env"%>
<%@page import="com.chargebee.framework.api.ApiStore"%>
<%@page import="com.chargebee.framework.api.ApiURLConfig"%>
<%@page import="com.chargebee.app.bulk_operations.actions.ProjectBulkOperationAction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ProjectBulkOperationAction act = (ProjectBulkOperationAction) ActionModel.getModel(request);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<t:vtabs tabName="inner" defTabs="<%= DataImportTabs.inst%>" activeDefProp="<%=InnerTabs.defprop%>" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form>
            <table>
            <tr>
                 <%        
                 for(InputParam p:act.id){
                    if(act.header.contains(p.getParamName())||act.header.contains("subscription["+p.getParamName()+"]")){
                    String entry=act.returnvalue(p);
                        if(entry.equals(""))
                            continue;
                 %>
                    <td>
                        <%=p.getParamName()%>
                    </td>
                    <%}}%>
            </tr>
             <tr>
                 <%        
                 for(InputParam p:act.id){
                     String selected="selected",checked="checked";
                     Column d = p.col;
                     if(act.header.contains(p.getParamName())||act.header.contains("subscription["+p.getParamName()+"]")){
                        String entry=act.returnvalue(p);
                        if(entry.equals(""))
                            continue;
                         if (d instanceof StringCol)
                {%>
            <td><input type="text" name="<%=p.getParamName()%>" value="<%=entry%>" > </td>
                <%
                }else if(d instanceof TimestampCol){%>
            <td><input type="date" name="<%=p.getParamName()%>" value="" > </td>
                
               <%}else if(d instanceof BooleanCol)
                 {%>
            <td>
                <input type="radio" name="<%=p.getParamName()%>" value= "true" <%= entry.equals("true")?checked:""%>>true</br>  
                <input type="radio" name="<%=p.getParamName()%>" value= "false" <%= entry.equals("false")?checked:""%>>false</td>
                <%}else if (d instanceof EnumColumn) {
                    EnumColumn e = (EnumColumn) d;
                    %>
            <td>
                <select name="<%=p.getParamName()%>" >
                    <%for (EnumEntry enu : e.visibleEntries()) {
                    %>
                       <option value="<%=enu.getValue()%>" <%= (entry.equals(enu.apiName())?selected:"") %>> <%=enu.apiName()%></option>
                    <%}%>
                </select>
            </td>
                <%}else{%>
                <td><input type="text" name="<%=p.getParamName()%>"  value="<%=entry%>" > </td>
                <%}}}%>
            </tr>
       
        </table>
        </form>    
    </body>
</html>
