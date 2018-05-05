<%-- 
    Document   : altas
    Created on : 05-may-2018, 21:56:48
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
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Perfil</a></li>
                </ul>
            </div>
        </nav>
        <div style="margin-top: 4%; margin-left: 36%;padding-left: 7%;padding-top: 75px;margin-right: 30%;padding-bottom: 2%;background: linear-gradient(500deg, #f598a880, #f6edb2cc); border-radius: 3%;
             -webkit-box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);-moz-box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);">
            <h3 style="font-family: 'Acme';margin-top: -12%;font-size: 40px;margin-left: 25%">Altas</h3>
            <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 5%;margin-left: -26%;" />
            <%
                String ciudad = (String) request.getAttribute("selectCiudad");
                if (ciudad == "true") {
            %>
            <form>
                <input type="text" maxlength="50">
            </form>
            <%
                }
            %>



        </div>
    </body>
</html>
