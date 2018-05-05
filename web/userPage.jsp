<%-- 
    Document   : UserPage
    Created on : 21-abr-2018, 15:01:06
    Author     : julio
--%>

<%@page import="modelo.Coche"%>
<%@page import="modelo.Tienda"%>
<%@page import="modelo.Ciudad"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Localizacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RentaCar</title> 
        <link href="https://fonts.googleapis.com/css?family=Ubuntu|Acme" rel="stylesheet">
        <link href="style/navbar.css" rel="stylesheet" type="text/css"/>
        <link href="style/selects.css" rel="stylesheet" type="text/css"/> 
        <link href="style/table.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
        <style>
            input {
                padding: .5em .6em;
                display: inline-block;
                border: 1px solid #ccc;
                box-shadow: inset 0 1px 3px #ddd;
                border-radius: 4px;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
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
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Perfil</a></li>
                </ul>
            </div>
        </nav>
        <div style="margin-top: 4%; margin-left: 36%;padding-left: 7%;padding-top: 75px;margin-right: 30%;padding-bottom: 2%;background: linear-gradient(500deg, #f598a880, #f6edb2cc); border-radius: 3%;
             -webkit-box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);-moz-box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);box-shadow: 0px 32px 56px -19px rgba(0,0,0,0.72);">
            <h3 style="font-family: 'Acme';margin-top: -12%;font-size: 40px;">Alquiler de Coches</h3>
            <hr style="color: #a59e9e;border-top: 2px solid;margin-top: 5%;margin-left: -26%;" />
            <%
                List<Ciudad> ciudades = (List<Ciudad>) request.getAttribute("ciudades");
                if (ciudades != null) {
                    if (ciudades.size() < 1) {
            %>
            <div class="wrap" style="margin-left: 8%">
                <div class="custom">
                    <p style="font-family: 'Ubuntu', cursive; color: #494949b3">Ciudad: 
                        <select name="user" required>
                            <option disabled>--ELIGE--</option>
                            <option>Ninguna ciudad disponible</option>
                        </select>
                    </p>
                </div>
            </div>
            <%
            } else {
            %>
            <form action="TiendasByCiudad" method="POST" style="margin-top: 5%; margin-left: 8%;">
                <p style="font-family: 'Ubuntu', cursive; color: darkred;font-size: 20px; color: #494949b3;">Ciudad:</p> 
                <div class="wrap">
                    <div class="custom">
                        <select name="ciudad" required>
                            <option disabled>--ELIGE--</option>
                            <%
                                for (Ciudad c : ciudades) {
                            %>
                            <option> <%= c.getNombre()%> </option>
                            <%
                                }
                            %>
                        </select>

                    </div>
                </div>
                <input type="submit" name="disponible" value="Disponibilidad" style="cursor: pointer; display: block;width: 55%;margin-left: 5%;margin-top: 4%;"/>
            </form>
            <%                }
                }
            %>
            <%
                List<Tienda> tiendas = (List<Tienda>) request.getAttribute("tiendas");
                if (tiendas != null) {
                    if (tiendas.size() < 1) {
            %>
            <div class="wrap" style="margin-left: 8%; margin-top: 5%">
                <div class="custom">
                    <p style="font-family: 'Ubuntu', cursive; color: #494949b3">Tienda 
                        <select name="tienda" required>
                            <option disabled>--ELIGE--</option>
                            <option>Ninguna tienda disponible</option>
                        </select>
                    </p>
                </div>
            </div>
            <%
            } else {
            %>
            <form action="CochesDisponibles" method="POST" style="margin-top: 5%; margin-left: 8%">
                <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px;">Tienda:</p> 
                <div class="wrap">
                    <div class="custom">
                        <select name="tienda" required>
                            <option disabled>--ELIGE--</option>
                            <%
                                for (Tienda t : tiendas) {
                            %>
                            <option value="<%= t.getId()%>"> <%= t.getNombre()%> - <%= t.getLocalizacion().getDireccion()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div style="display: block;">
                    <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: 4%">Fecha Recogida:</p> 
                    <input class="rango2" type="text" name="f_inicio" id="rango2_de" placeholder="mm-dd-yyyy" style="width: 66%" required/>
                    <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: 4%">Fecha Entrega:</p> 
                    <input class="rango2" type="text" name="f_final" id="rango2_a" placeholder="mm-dd-yyyy" style="width: 66%" required/>
                </div>
                <input name="consultar" type="submit" value="Consultar" style="cursor: pointer; display: block;width: 55%;margin-left: 5%;margin-top: 4%;"/>
            </form>
            <%                }
                }
            %>
            <%
                List<Coche> coches = (List<Coche>) request.getAttribute("coches");
                String days = (String.valueOf(request.getAttribute("dias")));
                String fInicio = (String.valueOf(request.getAttribute("fechaInicio")));
                String fFinal = (String.valueOf(request.getAttribute("fechaFinal")));
                String tiendaSelect = (String.valueOf(request.getAttribute("tiendaSelect")));
                if (coches != null) {
                    if (coches.size() < 1) {
            %>
            <form action="TiendasByCiudad" method="POST">
                <input type="submit" value="VOLVER" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
            </form>
            <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: -1%; margin-left: 15%">No hay coches disponibles</p> 
            <%
            } else {
            %>
            <form action="TiendasByCiudad" method="POST">
                <input type="submit" value="VOLVER" style="cursor: pointer; margin-left: -25%; margin-top: 2%">
            </form>
            <form>
                <table style="margin-left: -13%">
                    <p style="font-family: 'Ubuntu', cursive; color: #494949b3;font-size: 20px; margin-top: -1%; margin-left: 15%">Coches Disponibles</p> 
                    <tbody class="product-options">
                    <th></th>
                    <th>Modelo</th>
                    <th>Plazas</th>
                    <th>Combustible</th>
                    <th>Precio</th>

                    <%
                        int numDias = Integer.parseInt(days);
                        for (Coche c : coches) {
                            int PrecioTotal = c.getPrecio() * numDias;
                    %>
                    <tr>
                        <td>
                            <input type="radio" name="coche" value="<%= c.getId()%>" checked="checked" />
                            <input type="hidden" name="precioTotal" value="<%= PrecioTotal%>">
                        </td>
                        <td><%= c.getModelo()%></td>
                        <td><%= c.getPlazas()%></td>
                        <td><%= c.getCombustible()%></td>
                        <td><%= PrecioTotal%>€</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <input type="hidden" name="DateInicio" value="<%= fInicio%>">
                <input type="hidden" name="DateFinal" value="<%= fFinal%>">
                <input type="hidden" name="tiendaSeleccionada" value="<%= tiendaSelect%>">
                <input type="submit" value="Comprar Alquiler" style="cursor: pointer; margin-left: 20%; margin-top: 2%">
            </form>
            <%                }
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
        <!-- JS para controlar que la segunda fecha no es anterior a la primera -->
        <script>
            $.extend($.datepicker, {
                travelRanges: function (options) {

                    var settings = {
                        target: '.travel-dates',
                        maxDateToBook: '30',
                        dafaultDate: new Date(),
                        populateFirst: true
                    };

                    $.extend(settings, options);

                    $(settings.target).datepicker({
                        minDate: '0',
                        onSelect: function (selectedDate) {
                            var self = this;
                            if ($(self).is(settings.target + ":first")) {
                                var newMaxDate = $(settings.target).datepicker('getDate');
                                newMaxDate.setDate($(this).datepicker('getDate').getDate() + settings.maxDateToBook);
                                $(settings.target + ":last").datepicker("change", {
                                    "minDate": $(settings.target).datepicker('getDate'),
                                    "maxDate": newMaxDate
                                });
                            }
                        }
                    });

                    if (settings.populateFirst) {
                        $(settings.target + ":first").datepicker('setDate', settings.dafaultDate);
                    }
                }
            });

            $(document).ready(function () {
                $.datepicker.travelRanges({target: ".rango1"});
                $.datepicker.travelRanges({target: ".rango2", populateFirst: false});
            });
        </script>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>
