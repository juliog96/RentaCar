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
        <link href="https://fonts.googleapis.com/css?family=Ubuntu|Acme" rel="stylesheet">
        <link href="style/navbar.css" rel="stylesheet" type="text/css"/>
        <link href="style/button.css" rel="stylesheet" type="text/css"/>
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
                    <li><a href="/RentaCar/adminPage.jsp">Home</a></li>
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
            <h3 style="font-family: 'Acme';margin-top: -12%;font-size: 40px;text-align: center;margin-left: -25%">Opciones</h3>
            <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 5%;margin-left: -26%;" /> 
            <div style="text-align: center">
                <div style="display: flex;margin-left: 15%;margin-top: 3%;">
                    <form action="Altas" method="POST" style="margin-right: 5%;">
                        <input class="button" name="ciudad" type="submit" value="ALTA CIUDAD" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                    <form action="Eliminaciones" method="POST">
                        <input class="button" type="submit" name="ciudad" value="ELIMINAR CIUDAD" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                </div>
                <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 3%;margin-left: -26%;" />
                <div style="display: flex;margin-top: 3%;">
                    <form action="Altas" method="POST" style="margin-right: 5%;">
                        <input class="button" name="coche" type="submit" value="ALTA COCHE" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                    <form action="Eliminaciones" method="POST" style="margin-right: 5%;">
                        <input class="button" name="coche" type="submit" value="ELIMINAR COCHE" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                    <form action="Modificaciones" method="POST" style="margin-right: 5%;">
                        <input class="button" name="coche" type="submit" value="MODIFICAR COCHE" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                </div>
                <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 3%;margin-left: -26%;" />
                <div style="display: flex;margin-left: 7%;margin-top: 3%;">
                    <form action="Altas" method="POST" style="margin-right: 5%;">
                        <input class="button" name="localizacion" type="submit" value="ALTA LOCALIZACION" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                    <form action="Eliminaciones" method="POST">
                        <input class="button" name="localizacion" type="submit" value="ELIMINAR LOCALIZACION" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                </div>
                <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 3%;margin-left: -26%;" />
                <div style="display: flex;margin-top: 3%;">
                    <form action="Altas" method="POST" style="margin-right: 5%;">
                        <input class="button" name="tienda" type="submit" value="ALTA TIENDA" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                    <form action="Eliminaciones" method="POST" style="margin-right: 5%;">
                        <input class="button" name="tienda" type="submit" value="ELIMINAR TIENDA" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                    <form action="Modificaciones" method="POST" style="margin-right: 5%;">
                        <input class="button" name="tienda" type="submit" value="MODIFICAR TIENDA" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                    </form>
                </div>
                <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 3%;margin-left: -26%;" />
                <form action="Rankings" method="POST" style="margin-top: 3%;">
                    <input class="button" name="numFacturas" type="submit" value="RANKING TIENDA POR NUM FACTURAS" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                </form>
                <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 3%;margin-left: -26%;" />
                <form action="Rankings" method="POST" style="margin-top: 3%;">
                    <input class="button" name="facturacion" type="submit" value="TOP 3 TIENDAS MAYOR FACTURACION" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                </form>
                <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 3%;margin-left: -26%;" />
                <form action="EntrarComoUser" method="POST" style="margin-top: 3%;">
                    <input class="button" type="submit" value="ENTRAR COMO USUARIO" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
                </form>
            </div>
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
