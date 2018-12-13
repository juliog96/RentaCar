<%-- 
    Document   : rankings
    Created on : 12-may-2018, 19:17:35
    Author     : julio
--%>

<%@page import="modelo.RankingNumFacturas"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RentaCar</title>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu|Acme" rel="stylesheet">
        <link href="style/navbar.css" rel="stylesheet" type="text/css"/>
        <link href="style/button.css" rel="stylesheet" type="text/css"/>
        <link href="style/table.css" rel="stylesheet" type="text/css"/>
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
            <h3 style="font-family: 'Acme';margin-top: -12%;font-size: 40px;text-align: center;margin-left: -25%">Ranking</h3>
            <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 5%;margin-left: -26%;" /> 
            <%
                List<RankingNumFacturas> ranking = (List<RankingNumFacturas>) request.getAttribute("rankingFacturas");
                if (ranking != null) {
                    if (ranking.size() < 1) {
            %>
            <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: -1%; margin-left: 15%">No hay facturas</p> 
            <%
            } else {
            %>
            <table style="margin-left: -2%;margin-top: 3%;">
                <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: 3%; margin-left: 7%">Tiendas con mas facturas</p> 
                <tbody class="product-options">
                <th></th>
                <th>Tienda</th>
                <th>Nº Facturas</th>
                    <%
                        for (RankingNumFacturas r : ranking) {
                    %>
                <tr>
                    <td><%= r.getId()%></td>
                    <td><%= r.getTienda()%></td>
                    <td><%= r.getFacturas()%></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <%
                    }
                }
            %>
            <%
                List<RankingNumFacturas> rankingFact = (List<RankingNumFacturas>) request.getAttribute("rankingFacturacion");
                if (rankingFact != null) {
                    if (rankingFact.size() < 1) {
            %>
            <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: -1%; margin-left: 15%">No hay facturas</p> 
            <%
            } else {
            %>
            <table style="margin-left: -2%;margin-top: 3%;">
                <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: 3%; margin-left: -3%">TOP 3 - Tiendas con mas facturación</p> 
                <tbody class="product-options">
                <th></th>
                <th>Tienda</th>
                <th>Facturación</th>
                    <%
                        for (RankingNumFacturas r : rankingFact) {
                    %>
                <tr>
                    <td><%= r.getId()%></td>
                    <td><%= r.getTienda()%></td>
                    <td><%= r.getFacturas()%>€</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <%
                    }
                }
            %>
        </div>
    </body>
</html>
