<%-- 
    Document   : adminPage
    Created on : 24-abr-2018, 23:17:50
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RentaCar</title>
        <link href="style/navbar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="fixed-nav-bar">
            <div id="menu" class="menu">
                <img class="logo" src="images/Logo.png" width="25%" alt=""/>
                <!-- Example responsive navigation menu  -->
                <a class="show" href="#menu">Menu</a><a class="hide" href="#hidemenu">Menu</a>
                <ul class="menu-items">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Alquilar</a></li>
                    <li><a href="#">Opciones</a></li>
                    <li><a href="#">Perfil</a></li>
                </ul>
            </div>
        </nav>
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
