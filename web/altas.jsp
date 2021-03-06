<%-- 
    Document   : altas
    Created on : 05-may-2018, 21:56:48
    Author     : julio
--%>

<%@page import="modelo.Localizacion"%>
<%@page import="modelo.Ciudad"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Tienda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RentaCar</title>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu|Acme" rel="stylesheet">
        <link href="style/navbar.css" rel="stylesheet" type="text/css"/>
        <link href="style/selects.css" rel="stylesheet" type="text/css"/>
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
            <h3 style="font-family: 'Acme';margin-top: -12%;font-size: 40px;margin-left: 25%">Altas</h3>
            <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 5%;margin-left: -26%;" />
            <%
                String ciudad = (String) request.getAttribute("selectCiudad");
                if (ciudad == "true") {
            %>
            <h3 style="font-family: 'Acme';margin-top: 3%;font-size: 25px;margin-left: 26%">Ciudad</h3>
            <form action="Altas" method="Post" style="margin-left: 5%">
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Nombre:</p> 
                <input class="input" type="text" maxlength="50" name="nombreCiudad" required>
                <input type="submit" name="InsertCity" value="INSERTAR" style="display: block;margin-top: 5%;margin-left: 25%;padding: 3%;cursor: pointer;">
            </form>
            <%
                }
            %>
            <%
                String coche = (String) request.getAttribute("selectCoche");
                if (coche == "true") {
            %>
            <h3 style="font-family: 'Acme';margin-top: 3%;font-size: 25px;margin-left: 26%">Coche</h3>
            <form action="Altas" method="Post" style="margin-left: 5%">
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Matricula:</p> 
                <input class="input" type="text" maxlength="7" name="matricula" required>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Modelo:</p> 
                <input class="input" type="text" maxlength="50" name="modelo" required>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Plazas:</p> 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="plazas" required>
                            <option disabled selected>--ELIGE--</option>
                            <option>2</option>
                            <option>4</option>
                            <option>6</option>
                            <option>7+</option>
                        </select>
                    </div>
                </div>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Combustible:</p> 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="combustible" required>
                            <option disabled selected>--ELIGE--</option>
                            <option>Gasolina</option>
                            <option>Gasoil</option>
                            <option>Hibrido</option>
                            <option>Electrico</option>
                        </select>
                    </div>
                </div>
                <%
                    List<Tienda> tiendas = (List<Tienda>) request.getAttribute("tiendas");
                    if (tiendas != null) {
                        if (tiendas.size() < 1) {
                %>
                <p style="font-family: 'Ubuntu', cursive; color: #494949b3;margin-top: 3%">Tienda: 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="tienda" required>
                            <option disabled selected>--ELIGE--</option>
                            <option>Ninguna tienda disponible</option>
                        </select>
                        </p>
                    </div>
                </div>
                <%
                } else {
                %>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Tienda:</p> 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="tienda" required>
                            <option disabled selected>--ELIGE--</option>
                            <%
                                for (Tienda t : tiendas) {
                            %>
                            <option value="<%= t.getId()%>"> <%= t.getNombre()%> </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <%                }
                    }
                %>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Precio/Dia:</p> 
                <input class="input" type="number" name="precio" required>
                <input type="submit" name="InsertCar" value="INSERTAR" style="display: block;margin-top: 5%;margin-left: 25%;padding: 3%;cursor: pointer;">
            </form>
            <%
                }
            %>
            <%
                String localizacion = (String) request.getAttribute("selectLocalizacion");
                if (localizacion == "true") {
            %>
            <h3 style="font-family: 'Acme';margin-top: 3%;font-size: 25px;margin-left: 20%">Localizaciones</h3>
            <form action="Altas" method="Post" style="margin-left: 5%">
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Dirección:</p> 
                <input class="input" type="text" maxlength="100" name="direccion" required>
                <%
                    List<Ciudad> ciudades = (List<Ciudad>) request.getAttribute("ciudades");
                    if (ciudades != null) {
                        if (ciudades.size() < 1) {
                %>
                <p style="font-family: 'Ubuntu', cursive; color: #494949b3;margin-top: 3%">Ciudad: 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="ciudad" required>
                            <option disabled selected>--ELIGE--</option>
                            <option>Ninguna ciudad disponible</option>
                        </select>
                        </p>
                    </div>
                </div>
                <%
                } else {
                %>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Ciudad:</p> 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="ciudad" required>
                            <option disabled selected>--ELIGE--</option>
                            <%
                                for (Ciudad c : ciudades) {
                            %>
                            <option value="<%= c.getId()%>"> <%= c.getNombre()%> </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <%                }
                    }
                %>
                <input type="submit" name="InsertPlace" value="INSERTAR" style="display: block;margin-top: 5%;margin-left: 25%;padding: 3%;cursor: pointer;">
            </form>
            <%
                }
            %>
            <%
                String tienda = (String) request.getAttribute("selectTienda");
                if (tienda == "true") {
            %>
            <h3 style="font-family: 'Acme';margin-top: 3%;font-size: 25px;margin-left: 26%">Tienda</h3>
            <form action="Altas" method="Post" style="margin-left: 5%">
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Nombre:</p> 
                <input class="input" type="text" maxlength="100" name="nombre" required>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Empleados:</p> 
                <input class="input" type="number" maxlength="100" name="empleados" required>
                <%
                    List<Localizacion> localizaciones = (List<Localizacion>) request.getAttribute("localizaciones");
                    if (localizaciones != null) {
                        if (localizaciones.size() < 1) {
                %>
                <p style="font-family: 'Ubuntu', cursive; color: #494949b3;margin-top: 3%">Localización: 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="localizacion" required>
                            <option disabled selected>--ELIGE--</option>
                            <option>Ninguna localización disponible</option>
                        </select>
                        </p>
                    </div>
                </div>
                <%
                } else {
                %>
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;margin-top: 3%">Localización:</p> 
                <div class="wrap">
                    <div class="custom" style="width: 271px">
                        <select name="localizacion" required>
                            <option disabled selected>--ELIGE--</option>
                            <%
                                for (Localizacion l : localizaciones) {
                            %>
                            <option value="<%= l.getId()%>"> <%= l.getDireccion()%> </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <%                }
                    }
                %>
                <input type="submit" name="InsertShop" value="INSERTAR" style="display: block;margin-top: 5%;margin-left: 25%;padding: 3%;cursor: pointer;">
            </form>
            <%
                }
            %>
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
