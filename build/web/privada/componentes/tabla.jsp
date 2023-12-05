<%-- 
    Document   : tabla.jsp
    Created on : 12-04-2023, 07:55:21 PM
    Author     : Oliver
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/configuraciones.jsp"%>
    </head>
    <body>
        param1: <%= request.getParameter("encabezados")%><br />
        param2: <%= request.getParameter("datos")%>
    </body>
</html>
