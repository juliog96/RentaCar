<%-- 
    Document   : UserPage
    Created on : 21-abr-2018, 15:01:06
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String status = (String) request.getAttribute("status");
            if (status != null) {
        %>
        <script type="text/javascript">
            alert("<%= status%>");
        </script>
        <%
            }
        %>
    </body>
</html>
