<%-- 
    Document   : index
    Created on : 17-abr-2018, 14:20:38
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RentaCar</title>
        <link href="style/index.css" rel="stylesheet" type="text/css"/>
        <script defer src="https://use.fontawesome.com/releases/v5.0.9/js/all.js" integrity="sha384-8iPTk2s/jMVj81dnzb/iFR2sdA7u06vHJyyLlAd4snFpCl/SnyUjRrbdJsw1pGIl" crossorigin="anonymous"></script>    
    </head>
    <body>
        <div class="cotn_principal" style="background-image: url(images/Road_Login.jpg); background-size: cover">
            <div class="cont_centrar">
                <div class="cont_login">
                    <div class="cont_info_log_sign_up">
                        <div class="col_md_login">
                            <div class="cont_ba_opcitiy">
                                <h2>HOLA</h2>  
                                <p>Te estabamos esperando</p> 
                                <p></p> 
                                <button class="btn_login" onclick="cambiar_login()">LOGIN</button>
                            </div>
                        </div>
                        <div class="col_md_sign_up">
                            <div class="cont_ba_opcitiy">
                                <h2>BIENVENIDO</h2>
                                <p>Empieza a disfrutar de las vacaciones</p>
                                <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>
                            </div>
                        </div>
                    </div>
                    <div class="cont_back_info">
                        <div class="cont_img_back_grey">
                            <img src="images/cars.jpg" alt="" style="background-size: cover; margin-top: -4%"/>
                        </div>
                    </div>
                    <div class="cont_forms" >
                        <div class="cont_img_back_">
                            <img src="images/cars.jpg" alt="" style="background-size: cover; margin-top: -4%"/>
                        </div>
                        <div class="cont_form_login">
                            <a href="#" onclick="ocultar_login_sign_up()" ><i class="fas fa-angle-left"></i></a>
                            <h2>LOGIN</h2>
                            <form action="Login" method="POST">
                                <input type="text" placeholder="User" name="user" />
                                <input type="password" placeholder="Password" name="pass" />
                                <input type="submit" class="btn_login" value="LOGIN" style="color: white">
                            </form>
                        </div>
                        <div class="cont_form_sign_up">
                            <a href="#" onclick="ocultar_login_sign_up()"><i class="fas fa-angle-left"></i></a>
                            <h2>SIGN UP</h2>
                            <form action="Register" method="POST">
                                <input type="text" placeholder="User" name="user" maxlength="15" required/>
                                <input type="password" placeholder="Password" name="pass" maxlength="50" required/>
                                <input type="text" placeholder="Nombre" name="nombre" maxlength="50" required/>
                                <input type="text" placeholder="DNI" name="dni" maxlength="9" required/>
                                <input type="text" placeholder="Telefono" name="telefono" maxlength="9" required/>
                                <input type="submit" class="btn_sign_up" value="SIGN UP" style="color: white"/>
                            </form>
                        </div>
                    </div>
                </div>
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
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>
