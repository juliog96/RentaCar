<%-- 
    Document   : perfilAdmin
    Created on : 12-may-2018, 16:57:18
    Author     : julio
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RentaCar</title>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu|Acme" rel="stylesheet">
        <link href="style/navbar.css" rel="stylesheet" type="text/css"/>
        <link href="style/button.css" rel="stylesheet" type="text/css"/>
        <style>
            .input{
                padding: 15px 5px;
                margin-top: 5px;
                width: 260px;
                border: none;
                color: #757575;
                font-size: 18px;
            }
        </style>
    </head>
    <body style="background-image: url('images/Travel.jpg'); background-size: cover">
        <nav class="fixed-nav-bar">
            <div id="menu" class="menu">
                <img class="logo" src="images/Logo.png" width="25%" alt=""/>
                <form action="CerrarSesion" method="post" style="display: initial; cursor: pointer">
                    <input type="image" src="images/logout.png" alt="Submit Form" width="5%" style="margin-left: 20%"/>
                </form> 
                <!-- Example responsive navigation menu  -->
                <a class="show" href="#menu">Menu</a><a class="hide" href="#hidemenu">Menu</a>
                <ul class="menu-items">
                    <%
                        Usuario user = (Usuario) request.getAttribute("perfil");
                        if (user.getTipo().equals("Admin")) {
                    %>
                    <li><a href="/RentaCar/adminPage.jsp">Home</a></li>
                        <%
                        } else {
                        %>
                    <li>
                        <form action="EntrarComoUser" method="POST">
                            <input type="submit" value="Home" style="cursor: pointer; font-size: 18px">
                        </form>
                    </li>
                    <%
                        }
                    %>
                    <li>
                        <form action="Modificaciones" method="POST">
                            <input type="submit" name="Perfil" value="Perfil" style="cursor: pointer; font-size: 18px">
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
        <div style="margin-top: 4%; margin-left: 36%;padding-left: 7%;padding-top: 75px;margin-right: 30%;padding-bottom: 2%;background: linear-gradient(500deg, #f598a880, #f6edb2cc); border-radius: 3%;
             -webkit-box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);-moz-box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);">
            <h3 style="font-family: 'Acme';margin-top: -12%;font-size: 40px;margin-left: 25%">Perfil</h3>
            <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 5%;margin-left: -26%;" />

            <form action="Modificaciones" method="Post">
                <div style="margin-left: 8%">
                    <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;">Contraseña:</p> 
                    <input class="input" type="text" name="password" maxlength="50" value="<%= user.getPassword()%>" style="margin-bottom: 4%">

                    <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;">Nombre:</p> 
                    <input class="input" type="text" name="nombre" maxlength="50" value="<%= user.getNombre()%>" style="margin-bottom: 4%">

                    <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;">Apellido:</p> 
                    <input class="input" type="text" name="apellido" maxlength="50" value="<%= user.getApellido()%>" style="margin-bottom: 4%">

                    <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;">Codigo Postal:</p> 
                    <input class="input" type="text" name="cp" maxlength="5" value="<%= user.getCp()%>" style="margin-bottom: 4%">

                    <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;">Telefono:</p> 
                    <input class="input" type="text" maxlength="9" name="telefono" value="<%= user.getTelefono()%>" style="margin-bottom: 4%">
                    <input type="submit" name="ModPerfil" value="Modificar" style="display: block;margin-top: 5%;margin-left: 25%;padding: 3%;cursor: pointer;">
                </div>
            </form>
        </div>
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
